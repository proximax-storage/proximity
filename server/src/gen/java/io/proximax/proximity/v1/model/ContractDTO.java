package io.proximax.proximity.v1.model;

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
public class ContractDTO   {
  
  private @Valid String drive;
  private @Valid String owner;
  private @Valid List<String> replicators = new ArrayList<>();
  private @Valid String root;
  private @Valid Long created;
  private @Valid Long duration;
  private @Valid Long space;
  private @Valid Integer replicas;
  private @Valid Integer percentApprovers;
  private @Valid Long billingPrice;
  private @Valid Integer billingPeriod;

  /**
   * [Cid](https://github.com/multiformats/cid) (version 1) - special content identifier. 
   **/
  public ContractDTO drive(String drive) {
    this.drive = drive;
    return this;
  }

  

  
  @ApiModelProperty(value = "[Cid](https://github.com/multiformats/cid) (version 1) - special content identifier. ")
  @JsonProperty("drive")
  public String getDrive() {
    return drive;
  }

  public void setDrive(String drive) {
    this.drive = drive;
  }/**
   * Hex encoded public key.
   **/
  public ContractDTO owner(String owner) {
    this.owner = owner;
    return this;
  }

  

  
  @ApiModelProperty(value = "Hex encoded public key.")
  @JsonProperty("owner")
  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }/**
   * Hex encoded public keys.
   **/
  public ContractDTO replicators(List<String> replicators) {
    this.replicators = replicators;
    return this;
  }

  

  
  @ApiModelProperty(value = "Hex encoded public keys.")
  @JsonProperty("replicators")
  public List<String> getReplicators() {
    return replicators;
  }

  public void setReplicators(List<String> replicators) {
    this.replicators = replicators;
  }/**
   **/
  public ContractDTO root(String root) {
    this.root = root;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("root")
  public String getRoot() {
    return root;
  }

  public void setRoot(String root) {
    this.root = root;
  }/**
   * Block height when the Contract was started.
   **/
  public ContractDTO created(Long created) {
    this.created = created;
    return this;
  }

  

  
  @ApiModelProperty(value = "Block height when the Contract was started.")
  @JsonProperty("created")
  public Long getCreated() {
    return created;
  }

  public void setCreated(Long created) {
    this.created = created;
  }/**
   **/
  public ContractDTO duration(Long duration) {
    this.duration = duration;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("duration")
  public Long getDuration() {
    return duration;
  }

  public void setDuration(Long duration) {
    this.duration = duration;
  }/**
   **/
  public ContractDTO space(Long space) {
    this.space = space;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("space")
  public Long getSpace() {
    return space;
  }

  public void setSpace(Long space) {
    this.space = space;
  }/**
   **/
  public ContractDTO replicas(Integer replicas) {
    this.replicas = replicas;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("replicas")
  public Integer getReplicas() {
    return replicas;
  }

  public void setReplicas(Integer replicas) {
    this.replicas = replicas;
  }/**
   **/
  public ContractDTO percentApprovers(Integer percentApprovers) {
    this.percentApprovers = percentApprovers;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("percentApprovers")
  public Integer getPercentApprovers() {
    return percentApprovers;
  }

  public void setPercentApprovers(Integer percentApprovers) {
    this.percentApprovers = percentApprovers;
  }/**
   **/
  public ContractDTO billingPrice(Long billingPrice) {
    this.billingPrice = billingPrice;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("billingPrice")
  public Long getBillingPrice() {
    return billingPrice;
  }

  public void setBillingPrice(Long billingPrice) {
    this.billingPrice = billingPrice;
  }/**
   **/
  public ContractDTO billingPeriod(Integer billingPeriod) {
    this.billingPeriod = billingPeriod;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("billingPeriod")
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

