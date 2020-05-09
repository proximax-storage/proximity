/**
 * 
 */
package io.proximax.proximity.account.model;

/**
 * @author tono
 *
 */
public enum AccountStatus {
   /** active account with validated e-mail */
   ACTIVE("active"), 
   /** disabled account */
   DISABLED("disabled");
   
   private final String code;

   /**
    * @param code
    */
   private AccountStatus(String code) {
      this.code = code;
   }

   /**
    * @return the code
    */
   public String getCode() {
      return code;
   }
   
   /**
    * retrieve account status by the code - case sensitive
    * 
    * @param code
    * @return
    */
   public static AccountStatus getByCode(String code) {
      for (AccountStatus status: values()) {
         if (status.getCode().equals(code)) {
            return status;
         }
      }
      throw new IllegalArgumentException("Invalid status code " + code);
   }
}
