/**
 * 
 */
package io.proximax.proximity.exception;

/**
 * @author tono
 *
 */
public class ProximityException extends RuntimeException {
   private static final long serialVersionUID = -1934983547386724333L;

   private ProximityExceptionType type;
   
   /**
    * 
    */
   public ProximityException() {
   }

   /**
    * @param message
    */
   public ProximityException(ProximityExceptionType type, String message) {
      super(message);
      this.type = type;
   }

   /**
    * @param message
    * @param cause
    */
   public ProximityException(ProximityExceptionType type, String message, Throwable cause) {
      super(message, cause);
      this.type = type;
   }

   public ProximityExceptionType getType() {
      return type;
   }
}
