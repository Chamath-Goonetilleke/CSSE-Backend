package com.csse.backend.UserAndOrderManagement.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.sql.Date;

@Data
public class AcceptOrRejectPendingPr {

    @JsonProperty("supplierOrderQuotationId")
    private long supplierOrderQuotationId;

    @JsonProperty("rejectedDate")
    private Date orderRejectedDate;

    @JsonProperty("rejectedReason")
    private String rejectedReason;

    @JsonProperty("command")
    private boolean command;

}
