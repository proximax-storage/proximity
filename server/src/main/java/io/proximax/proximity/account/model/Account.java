/**
 * 
 */
package io.proximax.proximity.account.model;

import java.util.List;

import javax.persistence.*;

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

   @Column(name="privateKey", length=64, nullable=false)
   private String privateKey;

   @Column(name="status", length=10, nullable=false)
   @Enumerated(EnumType.STRING)
   private AccountStatus status;
   
   @OneToMany
   private List<ContractAssignment> contracts;
   
   /**
    * 
    */
   public Account() {}

   /**
    * @param id
    * @param email
    * @param emailValidation
    * @param passwordHash
    * @param token
    * @param status
    * @param contracts
    */
   public Account(String email, ValidationStatus emailValidation, String passwordHash, String token, String privateKey,
         AccountStatus status, List<ContractAssignment> contracts) {
      this.email = email;
      this.emailValidation = emailValidation;
      this.passwordHash = passwordHash;
      this.token = token;
      this.privateKey = privateKey;
      this.status = status;
      this.contracts = contracts;
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
    * @return the privateKey
    */
   public String getPrivateKey() {
      return privateKey;
   }

   /**
    * @param privateKey the privateKey to set
    */
   public void setPrivateKey(String privateKey) {
      this.privateKey = privateKey;
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
    * @return the contracts
    */
   public List<ContractAssignment> getContracts() {
      return contracts;
   }

   /**
    * @param contracts the contracts to set
    */
   public void setContracts(List<ContractAssignment> contracts) {
      this.contracts = contracts;
   }

}
