/**
 * 
 */
package io.proximax.proximity.util;

import java.util.function.Supplier;

import javax.inject.Singleton;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proximax.proximity.exception.ProximityException;
import io.proximax.proximity.exception.ProximityExceptionType;

/**
 * @author tono
 *
 */
public class HibernateSessionFactoryFactory implements Supplier<SessionFactory> {

   private static final Logger logger = LoggerFactory.getLogger(HibernateSessionFactoryFactory.class);

   public void dispose(SessionFactory sessionFactory) {
      if (sessionFactory != null && !sessionFactory.isClosed()) {
         logger.info("Closing sessionFactory");
         sessionFactory.close();
         sessionFactory = null;
      }
      logger.info("Released Hibernate sessionFactory resource");
   }

   @Override
   public SessionFactory get() {
      // A SessionFactory is set up once for an application from hibernate.cfg.xml
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
      try {
         return new MetadataSources(registry).buildMetadata().buildSessionFactory();
      } catch (Exception e) {
         logger.error("Failed to initialize session factory", e);
         // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
         // so destroy it manually.
         StandardServiceRegistryBuilder.destroy(registry);
         throw new ProximityException(ProximityExceptionType.ERROR, "failed to create session factory", e);
      }
   }

   public static class Binder extends AbstractBinder {
      @Override
      protected void configure() {
         bindFactory(HibernateSessionFactoryFactory.class).to(SessionFactory.class).in(Singleton.class);
      }
   }
}