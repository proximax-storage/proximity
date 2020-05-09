/**
 * 
 */
package io.proximax.proximity.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
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
public class ProximityContextFilter implements Filter {
   private static final Logger logger = LoggerFactory.getLogger(ProximityContextFilter.class);

   private SessionFactory sessionFactory;

   /**
    * 
    */
   public ProximityContextFilter() {
      // nothing special
   }

   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
      long start = System.currentTimeMillis();
      // A SessionFactory is set up once for an application from hibernate.cfg.xml
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
      try {
         sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
      } catch (Exception e) {
         // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
         // so destroy it manually.
         StandardServiceRegistryBuilder.destroy(registry);
         throw new ProximityException(ProximityExceptionType.ERROR, "failed to create session factory", e);
      }
      logger.info("Proximity context filter initialized in {}ms", System.currentTimeMillis() - start);
   }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
         throws IOException, ServletException {
      // make sure that hibernate session is open around the resource invocation
      try (Session session = sessionFactory.openSession()) {
         // store hibernate session to the shiro session for realms to retrieve
         ProximityContext.setPersistenceSesison(session);
         // invoke the processing down the chain
         chain.doFilter(request, response);
      } finally {
         // remove the hibernate session
         ProximityContext.removePersistenceSesison();
      }
   }

   @Override
   public void destroy() {
      // close hibernate session factory
      if (sessionFactory != null && !sessionFactory.isClosed()) {
         logger.info("Closing sessionFactory");
         sessionFactory.close();
         sessionFactory = null;
      }
      logger.info("Released Hibernate sessionFactory resource");
   }

}
