/**
 * 
 */
package io.proximax.proximity.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Supplier;

import javax.inject.Singleton;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proximax.dfms.DFMSClient;
import io.proximax.proximity.ProximityProperty;
import io.proximax.proximity.exception.ProximityException;
import io.proximax.proximity.exception.ProximityExceptionType;

/**
 * @author tono
 *
 */
public class StorageApiFactory implements Supplier<DFMSClient> {

   private static final Logger logger = LoggerFactory.getLogger(StorageApiFactory.class);

   @Override
   public DFMSClient get() {
      logger.info("reating instance of StorageApi");
      try {
         String urlString = ProximityProperty.SYSTEM_STORAGE_API_URL.getValue();
         if (urlString == null) {
            urlString = "http://localhost:6366";
         }
         URL storageApiUrl = new URL(urlString);
         return new DFMSClient(storageApiUrl);
      } catch (MalformedURLException e) {
         throw new ProximityException(ProximityExceptionType.ERROR, "Failed to instantiate StorageApi", e);
      }
   }

   public static class Binder extends AbstractBinder {
      @Override
      protected void configure() {
         bindFactory(StorageApiFactory.class).to(DFMSClient.class).in(Singleton.class);
      }
   }
}