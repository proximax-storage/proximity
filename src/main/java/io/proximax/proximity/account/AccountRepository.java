/**
 * 
 */
package io.proximax.proximity.account;

import java.util.Collections;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proximax.proximity.ProximityProperty;
import io.proximax.proximity.account.model.Account;
import io.proximax.proximity.account.model.AccountStatus;
import io.proximax.proximity.account.model.ValidationStatus;
import io.proximax.proximity.exception.ProximityException;
import io.proximax.proximity.exception.ProximityExceptionType;
import io.proximax.proximity.security.jwt.JWTSecurityUtils;
import io.proximax.proximity.util.HibernateUtil;

/**
 * @author tono
 *
 */
public class AccountRepository {
   private static final Logger logger = LoggerFactory.getLogger(AccountRepository.class);

   // TODO inject from central configuration
   private final PasswordService passSvc = new DefaultPasswordService();

   /**
    * @param sessionFactory
    */
   public AccountRepository() {
   }

   /**
    * register new user
    * 
    * @param email
    * @param password
    * @return
    */
   public synchronized Account register(Session session, String email, String password) {
      logger.info("Registering user {}", email);
      // validate email
      if (!EmailValidator.getInstance().isValid(email)) {
         logger.warn("Registration provided invalid email '{}'", email);
         throw new ProximityException(ProximityExceptionType.BAD_REQUEST, email + " is not valid email address");
      }
      final String login = email.toLowerCase();
      // create account
      String token = JWTSecurityUtils.createAuthToken(login);
      Account info = new Account(login, ValidationStatus.NOT_VALIDATED, passSvc.encryptPassword(password), token,
            AccountStatus.ACTIVE, Collections.emptySet());
      // store account
      storeAccount(session, info);
      // send e-mail validation request
      try {
         sendEmailToken(login);
      } catch (EmailException e) {
         throw new ProximityException(ProximityExceptionType.ERROR, "Failed to send validation e-mail", e);
      }
      // return account
      return info;
   }

   /**
    * login the user using specified credentials
    * 
    * @param email
    * @param password
    * @return
    */
   public Account login(Session session, String email, String password) {
      logger.info("Logging in user {}", email);
      String login = email.toLowerCase();
      UsernamePasswordToken token = new UsernamePasswordToken(login, password);
      // delegate login to Shiro
      SecurityUtils.getSubject().login(token);
      // process the data
      // retrieve account information and provide in response
      Account info = getAccountByMail(session, email);
      // renew the token
      info.setToken(JWTSecurityUtils.createAuthToken(login));
      updateAccount(session, info);
      return info;
   }

   /**
    * store new account to the database
    * 
    * @param info the account info
    */
   public void storeAccount(Session session, Account info) {
      Transaction tx = session.beginTransaction();
      try {
         session.save(info);
         tx.commit();
      } catch (HibernateException e) {
         HibernateUtil.rollback(tx);
         throw e;
      }
   }

   /**
    * update account information in the database
    * 
    * @param info the account info
    */
   public void updateAccount(Session session, Account info) {
      Transaction tx = session.beginTransaction();
      try {
         session.update(info);
         tx.commit();
      } catch (HibernateException e) {
         HibernateUtil.rollback(tx);
         throw e;
      }
   }

   /**
    * retrieve account by e-mail
    * 
    * @param mail account e-mail
    * @return the account
    */
   public Account getAccountByMail(Session session, String mail) {
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Account> cr = cb.createQuery(Account.class);
      Root<Account> root = cr.from(Account.class);
      cr.select(root).where(cb.equal(root.get("email"), mail));
      return session.createQuery(cr).getSingleResult();
   }

   /**
    * send email with account activation token
    * 
    * @param mailAddress the recipient of the activation mail
    * @throws EmailException
    */
   public void sendEmailToken(String mailAddress) throws EmailException {
      // TODO enable email validations
      if (true) {
         return;
      }
      // Create the email message
      HtmlEmail email = new HtmlEmail();
      email.setHostName(ProximityProperty.MAIL_HOST.getValue());
      email.addTo(mailAddress);
      email.setFrom(ProximityProperty.MAIL_SENDER_MAIL.getValue(), ProximityProperty.MAIL_SENDER_NAME.getValue());
      email.setSubject("New Proximity registration");

      String mailToken = JWTSecurityUtils.createEmailValidationToken(mailAddress);
      // TODO remove hard coded data + support localization
      String verificationLink = "http://localhost:8080/proximity/api/v1/account/validate?emailToken=" + mailToken;
      // set the HTML message
      email.setHtmlMsg("<html>please click this link to confirm your email address: <a href=\"" + verificationLink
            + "\">" + verificationLink + "</a></html>");

      // set the alternative message
      email.setTextMsg("Open " + verificationLink + " in browser to validate your account");

      // send the email
      email.send();
   }
}
