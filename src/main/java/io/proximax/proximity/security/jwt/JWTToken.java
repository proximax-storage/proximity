/**
 * 
 */
package io.proximax.proximity.security.jwt;

import org.apache.shiro.authc.BearerToken;

/**
 * Authentication token for the JWT realm
 */
public class JWTToken extends BearerToken {
   private static final long serialVersionUID = -8028860156399819060L;

   /**
    * @param token
    */
   public JWTToken(String token) {
      super(token);
   }

   /**
    * @param token
    * @param host
    */
   public JWTToken(String token, String host) {
      super(token, host);
   }

}
