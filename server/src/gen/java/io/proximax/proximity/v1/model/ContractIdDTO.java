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
public class ContractIdDTO   {
  
  private @Valid Long id;
  private @Valid String cid;

  /**
   * unique id of the contract
   **/
  public ContractIdDTO id(Long id) {
    this.id = id;
    return this;
  }

  

  
  @ApiModelProperty(value = "unique id of the contract")
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }/**
   * CID of the contract
   **/
  public ContractIdDTO cid(String cid) {
    this.cid = cid;
    return this;
  }

  

  
  @ApiModelProperty(value = "CID of the contract")
  @JsonProperty("cid")
  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContractIdDTO contractIdDTO = (ContractIdDTO) o;
    return Objects.equals(this.id, contractIdDTO.id) &&
        Objects.equals(this.cid, contractIdDTO.cid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContractIdDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cid: ").append(toIndentedString(cid)).append("\n");
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

