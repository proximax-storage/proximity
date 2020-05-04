/**
 * 
 */
package io.proximax.proximity.rest.v1.resources;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.proximax.proximity.account.AccountRepository;
import io.proximax.proximity.account.model.Account;
import io.proximax.proximity.account.model.ValidationStatus;
import io.proximax.proximity.security.jwt.JWTSecurityUtils;
import io.proximax.proximity.v1.api.AccountApi;
import io.proximax.proximity.v1.model.AccountLoginDTO;
import io.proximax.proximity.v1.model.AccountRequestDTO;
import io.proximax.proximity.v1.model.TokensDTO;
import io.swagger.annotations.Api;

/**
 * @author tono
 *
 */
@Path("/account")
@Api(description = "the account API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-05-01T16:14:08.942+02:00[Europe/Prague]")
public class AccountResource extends AccountApi {
   private static final Logger logger = LoggerFactory.getLogger(AccountResource.class);
   
   @Context
   private ServletContext context;
   @Inject
   private SessionFactory hibernate;
   
   /**
    * 
    */
   public AccountResource() {
   }

   @RequiresAuthentication
   @Override
   public Response accountGet() {
      logger.info("Retrieving account information");
      String mail = (String)SecurityUtils.getSubject().getPrincipal();
      try (Session session = hibernate.openSession()) {
         AccountRepository accounts = new AccountRepository();
         Account info = accounts.getAccountByMail(session, mail);
         return Response.ok().entity(info).build();
      }
   }

   
   @Override
   public Response accountValidate(String emailToken) {
      if (emailToken != null) {
         // parse the claims from the token
         Claims claims = JWTSecurityUtils.parseEmailValidationToken(emailToken);
         try (Session session = hibernate.openSession()) {
            AccountRepository accounts = new AccountRepository();
            Account info = accounts.getAccountByMail(session, claims.getSubject());
            info.setEmailValidation(ValidationStatus.VALIDATED);
            accounts.updateAccount(session, info);
            return Response.ok().entity(info).build();
         }
      }
      return Response.status(Response.Status.BAD_REQUEST).entity("Unsupported action").build();
   }

   @Override
   public Response accountLogin(AccountLoginDTO accountLoginDTO) {
      // store session manager to shiro session
      SecurityUtils.getSubject().getSession().setAttribute("hibernate", hibernate);
      try (Session session = hibernate.openSession()) {
         AccountRepository accounts = new AccountRepository();
         Account info = accounts.login(session, accountLoginDTO.getEmail(), accountLoginDTO.getPassword());
         return Response.ok().entity(info).build();
      }
   }

   @Override
   public Response accountRegister(AccountRequestDTO accountRequestDTO) {
      try (Session session = hibernate.openSession()) {
         AccountRepository accounts = new AccountRepository();
         final Account info = accounts
               .register(session, accountRequestDTO.getEmail(), accountRequestDTO.getPassword());
         return Response.ok().entity(info).build();
      }
   }

   @Override
   public Response accountTokens() {
      // TODO either secure or remove this end point as it allows for bypass of email validation
      // get user email
      String email = (String)SecurityUtils.getSubject().getPrincipal();
      // generate tokens
      TokensDTO tokens = new TokensDTO();
      tokens.setAuthentication(JWTSecurityUtils.createAuthToken(email));
      tokens.setEmailValidation(JWTSecurityUtils.createEmailValidationToken(email));
      return Response.ok().entity(tokens).build();
   }

}
