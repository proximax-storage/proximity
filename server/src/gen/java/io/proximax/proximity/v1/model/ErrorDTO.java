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



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-05-12T22:55:04.259+02:00[Europe/Prague]")
public class ErrorDTO   {
  
  private @Valid String message;

public enum CodeEnum {

    NUMBER_0(Integer.valueOf(0)), NUMBER_1(Integer.valueOf(1)), NUMBER_2(Integer.valueOf(2)), NUMBER_3(Integer.valueOf(3)), NUMBER_4(Integer.valueOf(4));


    private Integer value;

    CodeEnum (Integer v) {
        value = v;
    }

    public Integer value() {
        return value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static CodeEnum fromValue(Integer value) {
        for (CodeEnum b : CodeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

  private @Valid CodeEnum code;

public enum TypeEnum {

    ERROR(String.valueOf("error"));


    private String value;

    TypeEnum (String v) {
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
    public static TypeEnum fromValue(String value) {
        for (TypeEnum b : TypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

  private @Valid TypeEnum type;

  /**
   * Error message
   **/
  public ErrorDTO message(String message) {
    this.message = message;
    return this;
  }

  

  
  @ApiModelProperty(value = "Error message")
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }/**
   * * 0 - Generic - is generic error * 1 - Client - means the client made an invalid request. * 2 - Internal - means there&#39;s a bug in the implementation. * 3 - RateLimited - is returned when the operation has been rate-limited. * 4 - Forbidden - is returned when the client doesn&#39;t have permission to perform the requested operation. 
   **/
  public ErrorDTO code(CodeEnum code) {
    this.code = code;
    return this;
  }

  

  
  @ApiModelProperty(value = "* 0 - Generic - is generic error * 1 - Client - means the client made an invalid request. * 2 - Internal - means there's a bug in the implementation. * 3 - RateLimited - is returned when the operation has been rate-limited. * 4 - Forbidden - is returned when the client doesn't have permission to perform the requested operation. ")
  @JsonProperty("code")
  public CodeEnum getCode() {
    return code;
  }

  public void setCode(CodeEnum code) {
    this.code = code;
  }/**
   **/
  public ErrorDTO type(TypeEnum type) {
    this.type = type;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorDTO errorDTO = (ErrorDTO) o;
    return Objects.equals(this.message, errorDTO.message) &&
        Objects.equals(this.code, errorDTO.code) &&
        Objects.equals(this.type, errorDTO.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, code, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorDTO {\n");
    
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

