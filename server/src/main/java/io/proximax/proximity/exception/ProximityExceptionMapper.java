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
import io.proximax.proximity.v1.model.ErrorDTO;
import io.proximax.proximity.v1.model.ErrorDTO.CodeEnum;
import io.proximax.proximity.v1.model.ErrorDTO.TypeEnum;

/**
 * @author tono
 *
 */
@Provider
public class ProximityExceptionMapper implements ExceptionMapper<Throwable> {
   private static final Logger logger = LoggerFactory.getLogger(ProximityExceptionMapper.class);

   private static final String INTERNAL_SERVER_ERROR = "Internal server error";
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
         return Response.status(Status.INTERNAL_SERVER_ERROR).entity(createErrorDTO(ErrorType.INTERNAL, INTERNAL_SERVER_ERROR)).build();
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
         return Response.status(Status.INTERNAL_SERVER_ERROR).entity(createErrorDTO(ErrorType.INTERNAL, INTERNAL_SERVER_ERROR)).build();
      }
   }

   private static Response handle(ProximityException e) {
      Response.Status status;
      ErrorDTO error;
      switch (e.getType()) {
      case BAD_REQUEST:
         status = Status.BAD_REQUEST;
         error = createErrorDTO(ErrorType.CLIENT, e.getMessage());
         break;
      default:
         status = Status.INTERNAL_SERVER_ERROR;
         error = createErrorDTO(ErrorType.INTERNAL, INTERNAL_SERVER_ERROR);
      }
      return Response.status(status).entity(error).build();
   }

   public Response handle(AuthorizationException exception) {
      Status status;
      if (exception instanceof UnauthorizedException) {
         status = Status.FORBIDDEN;
      } else {
         status = Status.UNAUTHORIZED;
      }
      return Response.status(status).entity(createErrorDTO(ErrorType.FORBIDDEN, "Not authorized")).build();
   }
   
   private Response handle(JwtException e) {
      return Response.status(Status.BAD_REQUEST).entity(createErrorDTO(ErrorType.INTERNAL, INTERNAL_SERVER_ERROR)).build();
   }
   
   private static ErrorDTO createErrorDTO(ErrorType type, String message) {
      ErrorDTO err = new ErrorDTO();
      err.setType(TypeEnum.ERROR);
      err.setCode(CodeEnum.fromValue(type.getCode()));
      err.setMessage(message);
      return err;
   }
}