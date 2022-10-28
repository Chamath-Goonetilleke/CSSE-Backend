package com.csse.backend.Requisition.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String deliveryAddress;
    private String supplierId;
    private String supplierAddress;
    private String itemName;
    private Date deliveryDate;
    private int quantity;
    private float price;
    private String status;
    private String notes;
    private String siteManagerName;
    private String supplierName;
    private String unitType;

    public Order() {
    }

    public Order(Integer id, String deliveryAddress, String supplierId, String supplierAddress, String itemName, Date deliveryDate, int quantity, float price, String status, String notes, String siteManagerName, String supplierName, String unitType) {
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.supplierId = supplierId;
        this.supplierAddress = supplierAddress;
        this.itemName = itemName;
        this.deliveryDate = deliveryDate;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.notes = notes;
        this.siteManagerName = siteManagerName;
        this.supplierName = supplierName;
        this.unitType = unitType;
    }

    public Order(String deliveryAddress, String itemName, Date deliveryDate, int quantity, String notes, String siteManagerName, String supplierName, String unitType, String status) {
        this.deliveryAddress = deliveryAddress;
        this.itemName = itemName;
        this.deliveryDate = deliveryDate;
        this.quantity = quantity;
        this.notes = notes;
        this.siteManagerName = siteManagerName;
        this.supplierName = supplierName;
        this.unitType = unitType;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSiteManagerName() {
        return siteManagerName;
    }

    public void setSiteManagerName(String siteManagerName) {
        this.siteManagerName = siteManagerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
}
