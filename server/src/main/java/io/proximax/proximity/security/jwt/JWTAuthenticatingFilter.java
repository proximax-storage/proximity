/**
 * 
 */
package io.proximax.proximity.security.jwt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BearerHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.proximax.proximity.v1.model.ErrorDTO;
import io.proximax.proximity.v1.model.ErrorDTO.CodeEnum;
import io.proximax.proximity.v1.model.ErrorDTO.TypeEnum;

/**
 * Authentication filter that extracts JWT token from request header and passes that to authorization
 */
public class JWTAuthenticatingFilter extends BearerHttpAuthenticationFilter {
   private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticatingFilter.class);

   /**
    * bean constructor
    */
   public JWTAuthenticatingFilter() {
      super();
   }

   @Override
   protected AuthenticationToken createBearerToken(String token, ServletRequest request) {
      return new JWTToken(token, request.getRemoteHost());
   }

   @Override
   protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
      // prepare the response in parent implementation
      super.sendChallenge(request, response);
      // prepare content body
      ErrorDTO err = new ErrorDTO();
      err.setType(TypeEnum.ERROR);
      err.setCode(CodeEnum.NUMBER_4);
      err.setMessage("Not authorized");
      // prepare for serialization
      ObjectMapper objectMapper = new ObjectMapper();
      // add error to the response body
      HttpServletResponse httpResponse = WebUtils.toHttp(response);
      try {
         PrintWriter writer = httpResponse.getWriter();
         objectMapper.writeValue(writer, err);
         writer.flush();
      } catch (IOException e) {
         logger.error("failed to serialize error", e);
      }
      return false;
   }
   
   
}
