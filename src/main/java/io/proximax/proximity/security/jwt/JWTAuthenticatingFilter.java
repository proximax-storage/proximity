/**
 * 
 */
package io.proximax.proximity.security.jwt;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Authentication filter that extracts JWT token from request header and passes that to authorization
 */
public class JWTAuthenticatingFilter extends AuthenticatingFilter {
   private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticatingFilter.class);

   private static final String AUTHORIZATION_HEADER = "Authorization";
   private static final String AUTHORIZATION_BEARER = "Bearer ";

   /**
    * bean constructor
    */
   public JWTAuthenticatingFilter() {
      super();
   }

   @Override
   protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
      // create JWT token from the request header
      String authHeader = getAuthHeader(request);
      String token = authHeader.substring(AUTHORIZATION_BEARER.length());
      return new JWTToken(token);
   }

   @Override
   protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
      String authHeader = getAuthHeader(request);
      // if we have valid token then execute login
      if (authHeader != null && authHeader.startsWith(AUTHORIZATION_BEARER)) {
         logger.info("Authenticating using token in request header");
         return executeLogin(request, response);
      }
      // was unable to handle the request so leave it for the filter chain
      return true;
   }

   /**
    * get Authorization field from the request header
    * 
    * @param request
    * @return the Authorization header value as string or null if such value is not present
    */
   private String getAuthHeader(ServletRequest request) {
      HttpServletRequest httpRequest = WebUtils.toHttp(request);
      return httpRequest.getHeader(AUTHORIZATION_HEADER);
   }
}
