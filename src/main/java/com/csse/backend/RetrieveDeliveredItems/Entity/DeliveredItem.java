package com.csse.backend.RetrieveDeliveredItems.Entity;

import com.csse.backend.UserAndOrderManagement.domains.OrderItem;
import com.csse.backend.UserAndOrderManagement.domains.User;
import com.csse.backend.UserAndOrderManagement.enums.SupplierOrderQuotationStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "supplier_order_quotation")
public class DeliveredItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLIER_ORDER_QUOTATION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable = false)
    @JsonIgnore
    private User supplier;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ITEM_ID", nullable = false)
    private OrderItem orderItem;

    @Column(name = "SUPPLY_AMOUNT")
    private String supplyAmount;

    @Column(name = "SUPPLY_PRICE_PER_UNIT")
    private Float supplyPricePerUnit;

    @Column(name = "SUPPLY_BRAND")
    private String supplyBrand;

    @Column(name = "SUPPLY_DATE")
    private Date supplyDate;

    @Column(name = "ORDER_REJECTED_DATE")
    private Date orderRejectedDate;

    @Column(name = "ORDER_REJECTED_REASON")
    private String orderRejectedReason;

    @Column(name = "ADVICE_NOTE")
    private String adviceNote;

    @Column(name = "INVOICE")
    private String invoice;

    @Column(name = "SUPPLIER_ORDER_QUOTATION_STATUS")
    @Enumerated(EnumType.ORDINAL)
    private SupplierOrderQuotationStatus supplierOrderQuotationStatus;


}
