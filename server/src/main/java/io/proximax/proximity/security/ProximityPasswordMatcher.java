/**
 * 
 */
package io.proximax.proximity.security;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;

/**
 * @author tono
 *
 */
public class ProximityPasswordMatcher extends PasswordMatcher {

   /**
    * 
    */
   public ProximityPasswordMatcher() {
      setPasswordService(new DefaultPasswordService());
   }

}
