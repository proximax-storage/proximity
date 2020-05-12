/**
 * 
 */
package io.proximax.proximity.exception;

/**
 * @author tono
 *
 */
public enum ErrorType {
   GENERAL(0), CLIENT(1), INTERNAL(2), RATE_LIMITED(3), FORBIDDEN(4);
   
   private final int code;

   /**
    * @param code
    */
   private ErrorType(int code) {
      this.code = code;
   }

   /**
    * @return the code
    */
   public int getCode() {
      return code;
   }
}
