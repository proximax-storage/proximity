/**
 * 
 */
package io.proximax.proximity.security.userpass;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proximax.proximity.account.AccountRepository;
import io.proximax.proximity.account.model.Account;
import io.proximax.proximity.security.ProximityPasswordMatcher;
import io.proximax.proximity.util.ProximityContext;

/**
 * Realm performing authentication based on the specified user name and password
 */
public class UserPasswordRealm extends AuthorizingRealm {
   private static final Logger logger = LoggerFactory.getLogger(UserPasswordRealm.class);

   /**
    * 
    */
   public UserPasswordRealm() {
      setAuthenticationTokenClass(UsernamePasswordToken.class);
      setCredentialsMatcher(new ProximityPasswordMatcher());
   }

   @Override
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
      // authorization is handled by another realm
      return null;
   }

   @Override
   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
      UsernamePasswordToken upToken = (UsernamePasswordToken) token;
      // retrieve account for token
      Session session = ProximityContext.getPersistenceSession();
      AccountRepository accounts = new AccountRepository();
      Account account = accounts.getAccountByMail(session, upToken.getUsername());
      if (account == null) {
         throw new UnknownAccountException("No account found for user [" + upToken.getUsername() + "]");
      }
      logger.info("Authenticated user {}", account.getEmail());
      // provide account info to Shiro authentication
      return new SimpleAuthenticationInfo(account.getEmail(), account.getPasswordHash(), getName());
   }
}
