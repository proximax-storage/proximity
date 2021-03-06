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
import java.util.ArrayList;
import java.util.List;

/**
 * ContractDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-05-13T22:46:23.640+02:00[Europe/Prague]")
public class ContractDTO {
  public static final String SERIALIZED_NAME_DRIVE = "drive";
  @SerializedName(SERIALIZED_NAME_DRIVE)
  private String drive;

  public static final String SERIALIZED_NAME_OWNER = "owner";
  @SerializedName(SERIALIZED_NAME_OWNER)
  private String owner;

  public static final String SERIALIZED_NAME_REPLICATORS = "replicators";
  @SerializedName(SERIALIZED_NAME_REPLICATORS)
  private List<String> replicators = null;

  public static final String SERIALIZED_NAME_ROOT = "root";
  @SerializedName(SERIALIZED_NAME_ROOT)
  private String root;

  public static final String SERIALIZED_NAME_CREATED = "created";
  @SerializedName(SERIALIZED_NAME_CREATED)
  private Long created;

  public static final String SERIALIZED_NAME_DURATION = "duration";
  @SerializedName(SERIALIZED_NAME_DURATION)
  private Long duration;

  public static final String SERIALIZED_NAME_SPACE = "space";
  @SerializedName(SERIALIZED_NAME_SPACE)
  private Long space;

  public static final String SERIALIZED_NAME_REPLICAS = "replicas";
  @SerializedName(SERIALIZED_NAME_REPLICAS)
  private Integer replicas;

  public static final String SERIALIZED_NAME_PERCENT_APPROVERS = "percentApprovers";
  @SerializedName(SERIALIZED_NAME_PERCENT_APPROVERS)
  private Integer percentApprovers;

  public static final String SERIALIZED_NAME_BILLING_PRICE = "billingPrice";
  @SerializedName(SERIALIZED_NAME_BILLING_PRICE)
  private Long billingPrice;

  public static final String SERIALIZED_NAME_BILLING_PERIOD = "billingPeriod";
  @SerializedName(SERIALIZED_NAME_BILLING_PERIOD)
  private Integer billingPeriod;


  public ContractDTO drive(String drive) {
    
    this.drive = drive;
    return this;
  }

   /**
   * [Cid](https://github.com/multiformats/cid) (version 1) - special content identifier. 
   * @return drive
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "[Cid](https://github.com/multiformats/cid) (version 1) - special content identifier. ")

  public String getDrive() {
    return drive;
  }


  public void setDrive(String drive) {
    this.drive = drive;
  }


  public ContractDTO owner(String owner) {
    
    this.owner = owner;
    return this;
  }

   /**
   * Hex encoded public key.
   * @return owner
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Hex encoded public key.")

  public String getOwner() {
    return owner;
  }


  public void setOwner(String owner) {
    this.owner = owner;
  }


  public ContractDTO replicators(List<String> replicators) {
    
    this.replicators = replicators;
    return this;
  }

  public ContractDTO addReplicatorsItem(String replicatorsItem) {
    if (this.replicators == null) {
      this.replicators = new ArrayList<>();
    }
    this.replicators.add(replicatorsItem);
    return this;
  }

   /**
   * Hex encoded public keys.
   * @return replicators
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Hex encoded public keys.")

  public List<String> getReplicators() {
    return replicators;
  }


  public void setReplicators(List<String> replicators) {
    this.replicators = replicators;
  }


  public ContractDTO root(String root) {
    
    this.root = root;
    return this;
  }

   /**
   * Get root
   * @return root
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getRoot() {
    return root;
  }


  public void setRoot(String root) {
    this.root = root;
  }


  public ContractDTO created(Long created) {
    
    this.created = created;
    return this;
  }

   /**
   * Block height when the Contract was started.
   * @return created
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Block height when the Contract was started.")

  public Long getCreated() {
    return created;
  }


  public void setCreated(Long created) {
    this.created = created;
  }


  public ContractDTO duration(Long duration) {
    
    this.duration = duration;
    return this;
  }

   /**
   * Get duration
   * @return duration
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getDuration() {
    return duration;
  }


  public void setDuration(Long duration) {
    this.duration = duration;
  }


  public ContractDTO space(Long space) {
    
    this.space = space;
    return this;
  }

   /**
   * Get space
   * @return space
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getSpace() {
    return space;
  }


  public void setSpace(Long space) {
    this.space = space;
  }


  public ContractDTO replicas(Integer replicas) {
    
    this.replicas = replicas;
    return this;
  }

   /**
   * Get replicas
   * @return replicas
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getReplicas() {
    return replicas;
  }


  public void setReplicas(Integer replicas) {
    this.replicas = replicas;
  }


  public ContractDTO percentApprovers(Integer percentApprovers) {
    
    this.percentApprovers = percentApprovers;
    return this;
  }

   /**
   * Get percentApprovers
   * @return percentApprovers
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getPercentApprovers() {
    return percentApprovers;
  }


  public void setPercentApprovers(Integer percentApprovers) {
    this.percentApprovers = percentApprovers;
  }


  public ContractDTO billingPrice(Long billingPrice) {
    
    this.billingPrice = billingPrice;
    return this;
  }

   /**
   * Get billingPrice
   * @return billingPrice
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getBillingPrice() {
    return billingPrice;
  }


  public void setBillingPrice(Long billingPrice) {
    this.billingPrice = billingPrice;
  }


  public ContractDTO billingPeriod(Integer billingPeriod) {
    
    this.billingPeriod = billingPeriod;
    return this;
  }

   /**
   * Get billingPeriod
   * @return billingPeriod
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getBillingPeriod() {
    return billingPeriod;
  }


  public void setBillingPeriod(Integer billingPeriod) {
    this.billingPeriod = billingPeriod;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContractDTO contractDTO = (ContractDTO) o;
    return Objects.equals(this.drive, contractDTO.drive) &&
        Objects.equals(this.owner, contractDTO.owner) &&
        Objects.equals(this.replicators, contractDTO.replicators) &&
        Objects.equals(this.root, contractDTO.root) &&
        Objects.equals(this.created, contractDTO.created) &&
        Objects.equals(this.duration, contractDTO.duration) &&
        Objects.equals(this.space, contractDTO.space) &&
        Objects.equals(this.replicas, contractDTO.replicas) &&
        Objects.equals(this.percentApprovers, contractDTO.percentApprovers) &&
        Objects.equals(this.billingPrice, contractDTO.billingPrice) &&
        Objects.equals(this.billingPeriod, contractDTO.billingPeriod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(drive, owner, replicators, root, created, duration, space, replicas, percentApprovers, billingPrice, billingPeriod);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContractDTO {\n");
    sb.append("    drive: ").append(toIndentedString(drive)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    replicators: ").append(toIndentedString(replicators)).append("\n");
    sb.append("    root: ").append(toIndentedString(root)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    space: ").append(toIndentedString(space)).append("\n");
    sb.append("    replicas: ").append(toIndentedString(replicas)).append("\n");
    sb.append("    percentApprovers: ").append(toIndentedString(percentApprovers)).append("\n");
    sb.append("    billingPrice: ").append(toIndentedString(billingPrice)).append("\n");
    sb.append("    billingPeriod: ").append(toIndentedString(billingPeriod)).append("\n");
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

