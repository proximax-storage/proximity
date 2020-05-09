/**
 * 
 */
package io.proximax.proximity.util;

import javax.persistence.PersistenceException;

import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tono
 *
 */
public class HibernateUtil {
   private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

   /**
    * 
    */
   public HibernateUtil() {
   }

   /**
    * rollback transaction and handle exceptions
    * 
    * @param tx transaction to roll-back or null
    */
   public static void rollback(Transaction tx) {
      if (tx == null) {
         return;
      }
      try {
         tx.rollback();
      } catch (PersistenceException e) {
         logger.error("Rollback failed", e);
      } catch (IllegalStateException e) {
         logger.warn("Attempt to rollback inactive transaction", e);
      } catch (RuntimeException e) {
         logger.error("Unefpected error during rollback", e);
      }
   }
}
