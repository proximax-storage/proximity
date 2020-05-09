/**
 * 
 */
package io.proximax.proximity.account;

import java.util.Arrays;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.PasswordService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proximax.proximity.ProximityProperty;
import io.proximax.proximity.account.model.Account;
import io.proximax.proximity.account.model.AccountStatus;
import io.proximax.proximity.account.model.ContractAssignment;
import io.proximax.proximity.account.model.ValidationStatus;
import io.proximax.proximity.exception.ProximityException;
import io.proximax.proximity.exception.ProximityExceptionType;
import io.proximax.proximity.security.ProximityPasswordMatcher;
import io.proximax.proximity.security.jwt.JWTSecurityUtils;
import io.proximax.proximity.security.userpass.UserPasswordRealm;
import io.proximax.proximity.util.HibernateUtil;

/**
 * @author tono
 *
 */
public class AccountRepository {
   private static final Logger logger = LoggerFactory.getLogger(AccountRepository.class);

   /** password service used also with {@link UserPasswordRealm} */
   private final PasswordService passSvc = new ProximityPasswordMatcher().getPasswordService();

   /**
    * bean constructor
    */
   public AccountRepository() {
      // no extra init
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
      // store account
      Account info = generateNewAccount(session, login, password);
      // send e-mail validation request
      try {
         sendEmailToken(login);
      } catch (EmailException e) {
         throw new ProximityException(ProximityExceptionType.ERROR, "Failed to send validation e-mail", e);
      }
      // return account
      return info;
   }

   protected ContractAssignment createNewContract() {
      // TODO need to generate new default contract for every new account
      return new ContractAssignment("baegbeibondkkrhxfprzwrlgxxltavqhweh2ylhu4hgo5lxjxpqbpfsw2lu");
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
    * generate new account with default information
    * 
    * @param session
    * @param login
    * @param password
    * @return
    */
   protected Account generateNewAccount(Session session, String login, String password) {
      Transaction tx = session.beginTransaction();
      try {
         // create JWT
         String token = JWTSecurityUtils.createAuthToken(login);
         // create contract and add it to the session
         ContractAssignment defaultContract = createNewContract();
         session.save(defaultContract);
         // create account
         Account info = new Account(login, ValidationStatus.NOT_VALIDATED, passSvc.encryptPassword(password), token,
               AccountStatus.ACTIVE, Arrays.asList(defaultContract));
         session.save(info);
         // commit the transaction
         tx.commit();
         return info;
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
      // generate verification link
      String verificationLink = getEmailValidationLink(ProximityProperty.SYSTEM_BASE_URL.getValue(),
            "/api/v1",
            mailToken);

      // set the HTML message
      email.setHtmlMsg("<html>please click this link to confirm your email address: <a href=\"" + verificationLink
            + "\">" + verificationLink + "</a></html>");
      // set the alternative message
      email.setTextMsg("Open " + verificationLink + " in browser to validate your account");

      // send the email
      email.send();
   }

   /**
    * get link that can be used to validate e-mail
    * 
    * @param baseUrl
    * @param appPath
    * @param validationtoken
    * @return
    */
   protected static String getEmailValidationLink(String baseUrl, String appPath, String validationtoken) {
      return String.format("%s%s/account/validate?emailToken=%s", baseUrl, appPath, validationtoken);
   }
}
