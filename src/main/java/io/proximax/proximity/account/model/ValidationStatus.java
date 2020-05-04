/**
 * 
 */
package io.proximax.proximity.account.model;

/**
 * @author tono
 *
 */
public enum ValidationStatus {
   /** item has not been validated yet */
   NOT_VALIDATED("not_validated"), 
   /** item is validated */
   VALIDATED("validated");
   
   private final String code;

   /**
    * @param code
    */
   private ValidationStatus(String code) {
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
   public static ValidationStatus getByCode(String code) {
      for (ValidationStatus status: values()) {
         if (status.getCode().equals(code)) {
            return status;
         }
      }
      throw new IllegalArgumentException("Invalid status code " + code);
   }
}
