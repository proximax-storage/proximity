/**
 * 
 */
package io.proximax.proximity.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.JwtException;

/**
 * @author tono
 *
 */
@Provider
public class ProximityExceptionMapper implements ExceptionMapper<Throwable> {
   private static final Logger logger = LoggerFactory.getLogger(ProximityExceptionMapper.class);

   /**
    * bean constructor
    */
   public ProximityExceptionMapper() {
      /* no init needed */
   }

   @Override
   public Response toResponse(Throwable e) {
      logger.info("Mapping exception to response", e);
      if (e instanceof WebApplicationException) {
         return handle((WebApplicationException) e);
      } else if (e instanceof ProximityException) {
         return handle((ProximityException) e);
      } else if (e instanceof AuthorizationException) {
         return handle((AuthorizationException) e);
      } else if (e instanceof JwtException) {
         return handle((JwtException) e);
      } else {
         // handle any other exception
         return Response.status(Status.INTERNAL_SERVER_ERROR).build();
      }
   }

   /**
    * handle {@link WebApplicationException}
    * 
    * @param e
    * @return
    */
   private static Response handle(WebApplicationException e) {
      Response resp = e.getResponse();
      if (resp != null) {
         return resp;
      } else {
         return Response.status(Status.INTERNAL_SERVER_ERROR).build();
      }
   }

   private static Response handle(ProximityException e) {
      Response.Status status = null;
      switch (e.getType()) {
      case BAD_REQUEST:
         status = Status.BAD_REQUEST;
         break;
      default:
         status = Status.INTERNAL_SERVER_ERROR;
      }
      return Response.status(status).build();
   }

   public Response handle(AuthorizationException exception) {
      Status status;
      if (exception instanceof UnauthorizedException) {
         status = Status.FORBIDDEN;
      } else {
         status = Status.UNAUTHORIZED;
      }
      return Response.status(status).build();
   }
   
   private Response handle(JwtException e) {
      return Response.status(Status.BAD_REQUEST).build();
   }
}