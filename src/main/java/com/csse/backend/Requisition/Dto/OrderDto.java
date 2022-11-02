package com.csse.backend.Requisition.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {
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

}
