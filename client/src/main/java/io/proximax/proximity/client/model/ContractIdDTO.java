/*
 * Proximity API
 * Proximity API
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package io.proximax.proximity.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ContractIdDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-05-13T22:46:23.640+02:00[Europe/Prague]")
public class ContractIdDTO {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_CID = "cid";
  @SerializedName(SERIALIZED_NAME_CID)
  private String cid;


  public ContractIdDTO id(Long id) {
    
    this.id = id;
    return this;
  }

   /**
   * unique id of the contract
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "unique id of the contract")

  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public ContractIdDTO cid(String cid) {
    
    this.cid = cid;
    return this;
  }

   /**
   * CID of the contract
   * @return cid
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "CID of the contract")

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
