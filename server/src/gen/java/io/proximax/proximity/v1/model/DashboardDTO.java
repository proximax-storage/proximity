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
public class DashboardDTO   {
  
  private @Valid String hello;

  /**
   * hello world implementation
   **/
  public DashboardDTO hello(String hello) {
    this.hello = hello;
    return this;
  }

  

  
  @ApiModelProperty(value = "hello world implementation")
  @JsonProperty("hello")
  public String getHello() {
    return hello;
  }

  public void setHello(String hello) {
    this.hello = hello;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DashboardDTO dashboardDTO = (DashboardDTO) o;
    return Objects.equals(this.hello, dashboardDTO.hello);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hello);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DashboardDTO {\n");
    
    sb.append("    hello: ").append(toIndentedString(hello)).append("\n");
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

