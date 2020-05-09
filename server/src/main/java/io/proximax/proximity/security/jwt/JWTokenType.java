/**
 * 
 */
package io.proximax.proximity.security.jwt;

/**
 * @author tono
 *
 */
public enum JWTokenType {
   /** authentication token */
   AUTHENTICATION("authentication"), 
   /** email validation token */
   VALIDATION_EMAIL("emailValidation");
   
   private final String code;

   /**
    * @param code
    */
   private JWTokenType(String code) {
      this.code = code;
   }

   /**
    * @return the code
    */
   public String getCode() {
      return code;
   }
   
   /**
    * retrieve validation status by the code - case sensitive
    * 
    * @param code
    * @return
    */
   public static JWTokenType getByCode(String code) {
      for (JWTokenType status: values()) {
         if (status.getCode().equals(code)) {
            return status;
         }
      }
      throw new IllegalArgumentException("Invalid token type " + code);
   }
}
