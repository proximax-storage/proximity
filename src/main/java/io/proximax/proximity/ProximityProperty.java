/**
 * 
 */
package io.proximax.proximity;

import io.proximax.proximity.exception.ProximityException;
import io.proximax.proximity.exception.ProximityExceptionType;

/**
 * @author tono
 *
 */
public enum ProximityProperty {
   SYSTEM_BASE_URL("proximity_system_base_url", true),
   SYSTEM_STORAGE_API_URL("proximity_system_storage_api_url", false),
   JWT_SECRET("proximity_jwt_secret", false),
   MAIL_HOST("proximity_mail_host", true),
   MAIL_SENDER_MAIL("proximity_mail_sender_mail", true),
   MAIL_SENDER_NAME("proximity_mail_sender_name", true);
   
   private final String key;
   private final boolean mandatory;
   
   /**
    * @param key
    */
   private ProximityProperty(String key, boolean mandatory) {
      this.key = key;
      this.mandatory = mandatory;
   }

   /**
    * @return the key
    */
   public String getKey() {
      return key;
   }
   
   /**
    * get the system property value
    * @return
    */
   public String getValue() {
      String value = System.getProperty(key);
      if (mandatory && value == null) {
         throw new ProximityException(ProximityExceptionType.ERROR, "Mandatory property not specified " + key);
      }
      return value;
   }
}
