package io.proximax.proximity.v1.model;

import io.proximax.proximity.v1.model.ContractIdDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-05-13T22:46:24.699+02:00[Europe/Prague]")
public class AccountInfoDTO   {
  
  private @Valid Long id;
  private @Valid String email;

public enum EmailValidationEnum {

    VALIDATED(String.valueOf("validated")), NOT_VALIDATED(String.valueOf("not_validated"));


    private String value;

    EmailValidationEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static EmailValidationEnum fromValue(String value) {
        for (EmailValidationEnum b : EmailValidationEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

  private @Valid EmailValidationEnum emailValidation;
  private @Valid String passwordHash;
  private @Valid String token;

public enum StatusEnum {

    ACTIVE(String.valueOf("active")), INACTIVE(String.valueOf("inactive"));


    private String value;

    StatusEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
        for (StatusEnum b : StatusEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

  private @Valid StatusEnum status;
  private @Valid List<ContractIdDTO> contracts = new ArrayList<>();

  /**
   * assigned account ID
   **/
  public AccountInfoDTO id(Long id) {
    this.id = id;
    return this;
  }

  

  
  @ApiModelProperty(value = "assigned account ID")
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }/**
   * e-mail address for account verification
   **/
  public AccountInfoDTO email(String email) {
    this.email = email;
    return this;
  }

  

  
  @ApiModelProperty(value = "e-mail address for account verification")
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }/**
   **/
  public AccountInfoDTO emailValidation(EmailValidationEnum emailValidation) {
    this.emailValidation = emailValidation;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("emailValidation")
  public EmailValidationEnum getEmailValidation() {
    return emailValidation;
  }

  public void setEmailValidation(EmailValidationEnum emailValidation) {
    this.emailValidation = emailValidation;
  }/**
   * password hash for authentication
   **/
  public AccountInfoDTO passwordHash(String passwordHash) {
    this.passwordHash = passwordHash;
    return this;
  }

  

  
  @ApiModelProperty(value = "password hash for authentication")
  @JsonProperty("passwordHash")
  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }/**
   * compact form of last JWT token
   **/
  public AccountInfoDTO token(String token) {
    this.token = token;
    return this;
  }

  

  
  @ApiModelProperty(value = "compact form of last JWT token")
  @JsonProperty("token")
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }/**
   **/
  public AccountInfoDTO status(StatusEnum status) {
    this.status = status;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }/**
   **/
  public AccountInfoDTO contracts(List<ContractIdDTO> contracts) {
    this.contracts = contracts;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("contracts")
  public List<ContractIdDTO> getContracts() {
    return contracts;
  }

  public void setContracts(List<ContractIdDTO> contracts) {
    this.contracts = contracts;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountInfoDTO accountInfoDTO = (AccountInfoDTO) o;
    return Objects.equals(this.id, accountInfoDTO.id) &&
        Objects.equals(this.email, accountInfoDTO.email) &&
        Objects.equals(this.emailValidation, accountInfoDTO.emailValidation) &&
        Objects.equals(this.passwordHash, accountInfoDTO.passwordHash) &&
        Objects.equals(this.token, accountInfoDTO.token) &&
        Objects.equals(this.status, accountInfoDTO.status) &&
        Objects.equals(this.contracts, accountInfoDTO.contracts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, emailValidation, passwordHash, token, status, contracts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountInfoDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    emailValidation: ").append(toIndentedString(emailValidation)).append("\n");
    sb.append("    passwordHash: ").append(toIndentedString(passwordHash)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    contracts: ").append(toIndentedString(contracts)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


}

