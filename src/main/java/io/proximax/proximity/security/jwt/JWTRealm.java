/**
 * 
 */
package io.proximax.proximity.security.jwt;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import io.jsonwebtoken.Claims;

/**
 * This realm validates JWT token and authenticates user based on that
 */
public class JWTRealm extends AuthorizingRealm {

   /**
    * 
    */
   public JWTRealm() {
      // this realm takes only JWT tokens
      setAuthenticationTokenClass(JWTToken.class);
      // credentials are not validated by this realm as the token signature proves the validity
      setCredentialsMatcher(new AllowAllCredentialsMatcher());
   }

   @Override
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
      // authorization is handled by another realm
      return null;
   }

   @Override
   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
      // cast to the token that we accept
      JWTToken jwtToken = (JWTToken)token;
      // parse the token - if token can be parsed then that is our proof of validity
      Claims claims = JWTSecurityUtils.parseAuthToken(jwtToken.getToken());
      // return the authentication information. Credentials do not matter much because we accept all
      return new SimpleAccount(claims.getSubject(), jwtToken.getToken(), getName());
   }

}
