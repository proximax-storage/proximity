/**
 * 
 */
package io.proximax.proximity.util;

import java.util.function.Supplier;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proximax.dfms.StorageApi;
import io.proximax.proximity.drive.AuthenticatedDrive;

/**
 * @author tono
 *
 */
public class AuthenticatedDriveFactory implements Supplier<AuthenticatedDrive> {

   private static final Logger logger = LoggerFactory.getLogger(AuthenticatedDriveFactory.class);

   @Inject
   private StorageApi storage;

   @Override
   public AuthenticatedDrive get() {
      logger.info("creating instance of AuthenticatedDrive");
      return new AuthenticatedDrive(storage.createDriveRepository());
   }

   public static class Binder extends AbstractBinder {
      @Override
      protected void configure() {
         bindFactory(AuthenticatedDriveFactory.class).to(AuthenticatedDrive.class).in(Singleton.class);
      }
   }
}