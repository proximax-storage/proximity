/**
 * 
 */
package io.proximax.proximity.security.userpass;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import io.proximax.proximity.account.AccountRepository;
import io.proximax.proximity.account.model.Account;
import io.proximax.proximity.account.model.ValidationStatus;

/**
 * Realm performing authentication based on the specified user name and password
 */
public class UserPasswordRealm extends AuthorizingRealm {

   /**
    * 
    */
   public UserPasswordRealm() {
      setAuthenticationTokenClass(UsernamePasswordToken.class);
   }

   @Override
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
      // authorization is handled by another realm
      return null;
   }

   @Override
   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
      UsernamePasswordToken upToken = (UsernamePasswordToken) token;

      SessionFactory hibernate = (SessionFactory) SecurityUtils.getSubject().getSession().getAttribute("hibernate");
      // retrieve account for token
      try (Session session = hibernate.openSession()) {
         AccountRepository accounts = new AccountRepository();
         Account account = accounts.getAccountByMail(session, upToken.getUsername());
         if (account == null) {
            throw new UnknownAccountException("No account found for user [" + upToken.getUsername() + "]");
         }
         if (account.getEmailValidation() != ValidationStatus.VALIDATED) {
            throw new DisabledAccountException("Account e-mail is not activated");
         }
         // provide account info to Shiro authentication
         return new SimpleAuthenticationInfo(account.getEmail(), account.getPasswordHash(), getName());
      }
   }

}
