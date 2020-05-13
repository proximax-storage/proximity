package io.proximax.proximity.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-05-13T22:46:24.699+02:00[Europe/Prague]")
public class TokensDTO   {
  
  private @Valid String authentication;
  private @Valid String emailValidation;

  /**
   * newly generated authentication token
   **/
  public TokensDTO authentication(String authentication) {
    this.authentication = authentication;
    return this;
  }

  

  
  @ApiModelProperty(value = "newly generated authentication token")
  @JsonProperty("authentication")
  public String getAuthentication() {
    return authentication;
  }

  public void setAuthentication(String authentication) {
    this.authentication = authentication;
  }/**
   * newly generated email validation token
   **/
  public TokensDTO emailValidation(String emailValidation) {
    this.emailValidation = emailValidation;
    return this;
  }

  

  
  @ApiModelProperty(value = "newly generated email validation token")
  @JsonProperty("emailValidation")
  public String getEmailValidation() {
    return emailValidation;
  }

  public void setEmailValidation(String emailValidation) {
    this.emailValidation = emailValidation;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TokensDTO tokensDTO = (TokensDTO) o;
    return Objects.equals(this.authentication, tokensDTO.authentication) &&
        Objects.equals(this.emailValidation, tokensDTO.emailValidation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authentication, emailValidation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TokensDTO {\n");
    
    sb.append("    authentication: ").append(toIndentedString(authentication)).append("\n");
    sb.append("    emailValidation: ").append(toIndentedString(emailValidation)).append("\n");
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

