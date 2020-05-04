/**
 * 
 */
package io.proximax.proximity.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Realm that handles authorization (i.e. answers which permissions the subject has). Authentication is left
 * for other realms
 */
public class ProximityAuthorizingRealm extends AuthorizingRealm {

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
      // retrieve roles and permissions
      SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
      // TODO this is hard-coded for tests and needs to be retrieved from backing store
      if ("tono".equalsIgnoreCase(username)) {
         authInfo.addObjectPermission(new WildcardPermission("drive:add,ls:ztkpyhlvebkjmoafkcrl8bnkeefjrneeugj1edtyadvyryhp8"));
         authInfo.addRole("somerole");
      }
      return authInfo;
   }

   @Override
   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
      // this is disabled by supports returning false. We have other realms for authentication
      return null;
   }

}
