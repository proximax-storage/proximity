/**
 * 
 */
package io.proximax.proximity.account.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="accounts", 
      uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class Account {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="id", nullable=false, unique=true, length=11)
   private int id;
   
   @Column(name="email", length=254, nullable=false, unique=true)
   private String email;
   
   @Column(name="email_validation", length=16, nullable=false)
   @Enumerated(EnumType.STRING)
   private ValidationStatus emailValidation;
   
   @Column(name="pwd_hash", length=128, nullable=false)
   private String passwordHash;
   
   @Column(name="token", length=1024, nullable=false)
   private String token;

   @Column(name="status", length=10, nullable=false)
   @Enumerated(EnumType.STRING)
   private AccountStatus status;
   
   @ElementCollection(fetch = FetchType.EAGER)
   @CollectionTable(name="permissions", joinColumns=@JoinColumn(name="account_id"))
   @Column(name="permission")
   private Set<String> permissions;
   
   /**
    * 
    */
   public Account() {}

   /**
    * @param email
    * @param emailValidation
    * @param passwordHash
    * @param token
    * @param status
    * @param permissions
    */
   public Account(String email, ValidationStatus emailValidation, String passwordHash, String token,
         AccountStatus status, Set<String> permissions) {
      this.email = email;
      this.emailValidation = emailValidation;
      this.passwordHash = passwordHash;
      this.token = token;
      this.status = status;
      this.permissions = permissions;
   }

   /**
    * @return the id
    */
   public int getId() {
      return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(int id) {
      this.id = id;
   }

   /**
    * @return the email
    */
   public String getEmail() {
      return email;
   }

   /**
    * @param email the email to set
    */
   public void setEmail(String email) {
      this.email = email;
   }

   /**
    * @return the emailValidation
    */
   public ValidationStatus getEmailValidation() {
      return emailValidation;
   }

   /**
    * @param emailValidation the emailValidation to set
    */
   public void setEmailValidation(ValidationStatus emailValidation) {
      this.emailValidation = emailValidation;
   }

   /**
    * @return the passwordHash
    */
   public String getPasswordHash() {
      return passwordHash;
   }

   /**
    * @param passwordHash the passwordHash to set
    */
   public void setPasswordHash(String passwordHash) {
      this.passwordHash = passwordHash;
   }

   /**
    * @return the token
    */
   public String getToken() {
      return token;
   }

   /**
    * @param token the token to set
    */
   public void setToken(String token) {
      this.token = token;
   }

   /**
    * @return the status
    */
   public AccountStatus getStatus() {
      return status;
   }

   /**
    * @param status the status to set
    */
   public void setStatus(AccountStatus status) {
      this.status = status;
   }

   /**
    * @return the permissions
    */
   public Set<String> getPermissions() {
      return permissions;
   }

   /**
    * @param permissions the permissions to set
    */
   public void setPermissions(Set<String> permissions) {
      this.permissions = permissions;
   }
   
}
