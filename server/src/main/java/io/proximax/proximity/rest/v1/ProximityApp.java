/**
 * 
 */
package io.proximax.proximity.rest.v1;

import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.UriBuilder;

import org.apache.shiro.web.jaxrs.ShiroAnnotationFilterFeature;
import org.apache.shiro.web.jaxrs.SubjectPrincipalRequestFilter;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import io.proximax.proximity.exception.ProximityExceptionMapper;
import io.proximax.proximity.rest.v1.resources.AccountResource;
import io.proximax.proximity.rest.v1.resources.DriveResource;
import io.proximax.proximity.util.AuthenticatedDriveFactory;
import io.proximax.proximity.util.StorageApiFactory;

/**
 * @author tono
 *
 */
@ApplicationPath("/api/v1")
public class ProximityApp extends Application {

   @Override
   public Set<Class<?>> getClasses() {
      Set<Class<?>> classes = new HashSet<>();
      // add mapper for proximity exceptions
      classes.add(ProximityExceptionMapper.class);
      // add shiro security
      classes.add(SubjectPrincipalRequestFilter.class);
      classes.add(ShiroAnnotationFilterFeature.class);
      // add resources
      classes.add(AccountResource.class);
      classes.add(DriveResource.class);
      // return the classes
      return classes;
   }

   @Override
   public Set<Object> getSingletons() {
      Set<Object> singletons = new HashSet<>();
      // add app specific singleton binders
      singletons.add(new StorageApiFactory.Binder());
      singletons.add(new AuthenticatedDriveFactory.Binder());
      return singletons;
   }

   @Override
   public Map<String, Object> getProperties() {
      return super.getProperties();
   }

   /**
    * run the app from command line
    * 
    * @param args
    * @throws IOException
    */
   public static void main(String[] args) throws IOException {
      URI baseUri = UriBuilder.fromUri("http://localhost/proximity").port(8080).build();
      ResourceConfig resConfig = new ResourceConfig(ProximityApp.class);
      GrizzlyHttpServerFactory.createHttpServer(baseUri, resConfig);
   }
}
