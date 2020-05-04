/**
 * 
 */
package io.proximax.proximity.security.jwt;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.proximax.dfms.cid.multibase.Multibase;
import io.proximax.proximity.ProximityProperty;

/**
 * Utility class that helps with JWT handling
 */
public class JWTSecurityUtils {
   private static final Logger logger = LoggerFactory.getLogger(JWTSecurityUtils.class);

   public static final SignatureAlgorithm SIGNATURE_ALG = SignatureAlgorithm.HS256;
   
   /** token will expire after 24 hours */
   public static final long TOKEN_EXPIRATION_MILLIS = 24*3600*1000l;
   
   /** the secret used for JWT signing and validation */
   private static final byte[] SECRET = getSecret();

   /**
    * do not instantiate this
    */
   private JWTSecurityUtils() {/* hide constructor of utility class */}
   
   /**
    * create new token for given user and his default contract
    * 
    * @param email
    * @param type
    * @param expiration
    * @return
    */
   public static String createToken(String email, JWTokenType type, Date expiration) {
      // create signing key
      Key signingKey = new SecretKeySpec(SECRET, SIGNATURE_ALG.getJcaName());
      // initialize builder for the token
      JwtBuilder builder = Jwts.builder().setAudience(type.getCode()).setSubject(email)
            .setExpiration(expiration);
      // sign and serialize to compact string
      return builder.signWith(SIGNATURE_ALG, signingKey).compact();
   }

   /**
    * create new token for given user and his default contract
    * 
    * @param email
    * @return
    */
   public static String createAuthToken(String email) {
      return createToken(email, JWTokenType.AUTHENTICATION, getExpirationDate());
   }

   /**
    * create new token for given user and his default contract
    * 
    * @param email
    * @return
    */
   public static String createEmailValidationToken(String email) {
      return createToken(email, JWTokenType.VALIDATION_EMAIL, getExpirationDate());
   }

   /**
    * parse the claims out of the token
    * 
    * @param token
    * @param type
    * @return
    */
   public static Claims parseToken(String token, JWTokenType type) {
      Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
      if (type.getCode().equals(claims.getAudience())) {
         return claims;
      } else {
         throw new JwtException("Unexpected token type");
      }
   }

   /**
    * parse the claims out of the authentication token
    * 
    * @param token
    * @return
    */
   public static Claims parseAuthToken(String token) {
      return parseToken(token, JWTokenType.AUTHENTICATION);
   }

   /**
    * parse the claims out of the email validation token
    * 
    * @param token
    * @return
    */
   public static Claims parseEmailValidationToken(String token) {
      return parseToken(token, JWTokenType.VALIDATION_EMAIL);
   }

   /**
    * get expiration date of one hour starting now
    * 
    * @return Date instance an hour in the future
    */
   private static Date getExpirationDate() {
      return new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_MILLIS);
   }
   
   /**
    * get secret used for JWT signature
    * 
    * @return bytes of the token
    */
   private static byte[] getSecret() {
      // try env variable
      String secretConfig = ProximityProperty.JWT_SECRET.getValue();
      if (secretConfig != null) {
         logger.info("Decoding JWT secret defined in 'proximity.jwt.secret' property as multibase string");
         return Multibase.decode(secretConfig);
      } else {
         logger.warn("Using random 32 bytes as JWT secret! Set property 'proximity.jwt.secret' to Multibase encoded secret");
         SecureRandom random = new SecureRandom();
         byte[] secret = new byte[32];
         random.nextBytes(secret);
         return secret;
      }
   }
}
