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



public class AccountInfoDTO   {
  
  private @Valid Long id;
  private @Valid String email;

public enum StatusEnum {

    NEW(String.valueOf("new")), ACTIVE(String.valueOf("active")), INACTIVE(String.valueOf("inactive"));


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
  private @Valid String token;

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
  }

  /**
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
  }

  /**
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
  }

  /**
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
        Objects.equals(this.status, accountInfoDTO.status) &&
        Objects.equals(this.token, accountInfoDTO.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, status, token);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountInfoDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
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

