/**
 * 
 */
package io.proximax.proximity.rest.v1.resources;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.proximax.proximity.account.AccountRepository;
import io.proximax.proximity.account.model.Account;
import io.proximax.proximity.account.model.ValidationStatus;
import io.proximax.proximity.security.jwt.JWTSecurityUtils;
import io.proximax.proximity.util.ProximityContext;
import io.proximax.proximity.v1.api.AccountApi;
import io.proximax.proximity.v1.model.AccountInfoDTO;
import io.proximax.proximity.v1.model.AccountInfoDTO.EmailValidationEnum;
import io.proximax.proximity.v1.model.AccountInfoDTO.StatusEnum;
import io.proximax.proximity.v1.model.AccountLoginDTO;
import io.proximax.proximity.v1.model.AccountRequestDTO;
import io.proximax.proximity.v1.model.TokensDTO;

/**
 * @author tono
 *
 */
@Path("/account")
public class AccountResource extends AccountApi {
   private static final Logger logger = LoggerFactory.getLogger(AccountResource.class);

   private final AccountRepository accounts = new AccountRepository();

   /**
    * 
    */
   public AccountResource() {
      // nothing special
   }

   @RequiresAuthentication
   @Override
   public Response accountGet() {
      String mail = (String) SecurityUtils.getSubject().getPrincipal();
      logger.info("Retrieving account information for {}", mail);
      Session session = ProximityContext.getPersistenceSession();
      Account info = accounts.getAccountByMail(session, mail);
      return Response.ok().entity(mapToDTO(info)).build();
   }

   @Override
   public Response accountValidate(String emailToken) {
      if (emailToken != null) {
         // parse the claims from the token
         Claims claims = JWTSecurityUtils.parseEmailValidationToken(emailToken);
         Session session = ProximityContext.getPersistenceSession();
         Account info = accounts.getAccountByMail(session, claims.getSubject());
         info.setEmailValidation(ValidationStatus.VALIDATED);
         accounts.updateAccount(session, info);
         return Response.ok().entity(mapToDTO(info)).build();
      }
      return Response.status(Response.Status.BAD_REQUEST).entity("Unsupported action").build();
   }

   @Override
   public Response accountLogin(AccountLoginDTO accountLoginDTO) {
      Session session = ProximityContext.getPersistenceSession();
      Account info = accounts.login(session, accountLoginDTO.getEmail(), accountLoginDTO.getPassword());
      return Response.ok().entity(mapToDTO(info)).build();
   }

   protected static AccountInfoDTO mapToDTO(Account info) {
      AccountInfoDTO account = new AccountInfoDTO();
      account.setId((long)info.getId());
      account.setEmail(info.getEmail());
      account.setEmailValidation(EmailValidationEnum.fromValue(info.getEmailValidation().getCode()));
      account.setStatus(StatusEnum.fromValue(info.getStatus().getCode()));
      account.setToken(info.getToken());
      account.setPasswordHash(info.getPasswordHash());
      return account;
   }
   
   @Override
   public Response accountRegister(AccountRequestDTO accountRequestDTO) {
      Session session = ProximityContext.getPersistenceSession();
      try {
         final Account info = accounts.register(session, accountRequestDTO.getEmail(), accountRequestDTO.getPassword());
         return Response.ok().entity(mapToDTO(info)).build();
      } catch (ConstraintViolationException e) {
         // failed to save account
         throw new WebApplicationException(Response.Status.BAD_REQUEST);
      }
   }

   @Override
   @RequiresAuthentication
   public Response accountTokens() {
      logger.warn("Account tokens retrieved via dev API!!!");
      // TODO either secure or remove this end point as it allows for bypass of email validation
      // get user email
      String email = (String) SecurityUtils.getSubject().getPrincipal();
      // generate tokens
      TokensDTO tokens = new TokensDTO();
      tokens.setAuthentication(JWTSecurityUtils.createAuthToken(email));
      tokens.setEmailValidation(JWTSecurityUtils.createEmailValidationToken(email));
      return Response.ok().entity(tokens).build();
   }

}
