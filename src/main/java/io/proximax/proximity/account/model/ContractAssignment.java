/**
 * 
 */
package io.proximax.proximity.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="contract_assignments", 
      uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class ContractAssignment {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="id", nullable=false, unique=true, length=11)
   private int id;
   
   @Column(name="cid", length=64, nullable=false, unique=false)
   private String cid;

   /**
    * 
    */
   public ContractAssignment() {
   }

   /**
    * @param id
    * @param cid
    */
   public ContractAssignment(String cid) {
      this.cid = cid;
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
    * @return the cid
    */
   public String getCid() {
      return cid;
   }

   /**
    * @param cid the cid to set
    */
   public void setCid(String cid) {
      this.cid = cid;
   }
      
}
