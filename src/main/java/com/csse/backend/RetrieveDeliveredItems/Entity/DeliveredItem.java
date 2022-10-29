package com.csse.backend.RetrieveDeliveredItems.Entity;

import com.csse.backend.domains.OrderItem;
import com.csse.backend.domains.User;
import com.csse.backend.enums.SupplierOrderQuotationStatus;
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
    @Column(name = "supplier_order_quotation_id")
    private Long id;

    @Column(name = "user_id")
    private Long supplier;

    @Column(name = "order_item_id")
    private Long orderItem;

    @Column(name = "supply_amount")
    private String supplyAmount;

    @Column(name = "supply_price_per_unit")
    private Float supplyPricePerUnit;

    @Column(name = "supply_brand")
    private String supplyBrand;

    @Column(name = "supply_date")
    private Date supplyDate;

    @Column(name = "order_rejected_date")
    private Date orderRejectedDate;

    @Column(name = "order_rejected_reason")
    private String orderRejectedReason;

    @Column(name = "advice_note")
    private String adviceNote;

    @Column(name = "invoice")
    private String invoice;

    @Column(name = "supplier_order_quotation_status")
    @Enumerated(EnumType.ORDINAL)
    private SupplierOrderQuotationStatus supplierOrderQuotationStatus;

}
