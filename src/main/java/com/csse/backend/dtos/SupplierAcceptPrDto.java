package com.csse.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class SupplierAcceptPrDto implements Serializable {

    @JsonProperty("supplierId")
    private Long supplierId;

    @JsonProperty("orderItemId")
    private Long orderItemId;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("unitPrice")
    private Float unitPrice;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("dateCanDeliver")
    private Date dateCanDeliver;

}
