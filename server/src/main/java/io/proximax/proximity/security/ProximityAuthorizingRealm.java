/**
 * 
 */
package io.proximax.proximity.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proximax.dfms.cid.Cid;
import io.proximax.proximity.account.AccountRepository;
import io.proximax.proximity.account.model.Account;
import io.proximax.proximity.account.model.ContractAssignment;
import io.proximax.proximity.account.model.ValidationStatus;
import io.proximax.proximity.util.ProximityContext;

/**
 * Realm that handles authorization (i.e. answers which permissions the subject has). Authentication is left
 * for other realms
 */
public class ProximityAuthorizingRealm extends AuthorizingRealm {
   private static final Logger logger = LoggerFactory.getLogger(ProximityAuthorizingRealm.class);

   private final AccountRepository accounts = new AccountRepository();
   
   /**
    * 
    */
   public ProximityAuthorizingRealm() {
   }

   
   @Override
   public boolean supports(AuthenticationToken token) {
      // disable authentication and only provide authorization
      return false;
   }


   @Override
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
      // retrieve the unique ID of the subject
      String username = (String)principals.getPrimaryPrincipal();
      Session session = ProximityContext.getPersistenceSession();
      logger.debug("Retrieving authorizations for user {}. Session is {}", username, session);
      Account account = accounts.getAccountByMail(session, username);
      // retrieve roles and permissions if account mail has been validated
      SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
      if (ValidationStatus.VALIDATED == account.getEmailValidation()) {
         // grant access to drives for owned contracts
         for (ContractAssignment contract: account.getContracts()) {
            Permission permission = ProximityPermissions.drivePermission(Cid.decode(contract.getCid()), "*");
            logger.info("granting {} to {}", username, permission);
            authInfo.addObjectPermission(permission);
         }
      }
      logger.info("Loaded authorizations: {}", authInfo);
      return authInfo;
   }

   @Override
   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
      // this is disabled by supports returning false. We have other realms for authentication
      return null;
   }

}
