/**
 * 
 */
package io.proximax.proximity.util;

import org.apache.shiro.SecurityUtils;
import org.hibernate.Session;

/**
 * @author tono
 *
 */
public class ProximityContext {
   
   private static final String SESSION = "io.proximity.persistence.session";

   /**
    * 
    */
   private ProximityContext() {
      // nothing special
   }

   public static Session getPersistenceSession() {
      return (Session)SecurityUtils.getSubject().getSession().getAttribute(SESSION);
   }
   
   public static void setPersistenceSesison(Session session) {
      SecurityUtils.getSubject().getSession().setAttribute(SESSION, session);
   }
   
   public static void removePersistenceSesison() {
      SecurityUtils.getSubject().getSession().removeAttribute(SESSION);
   }
}
