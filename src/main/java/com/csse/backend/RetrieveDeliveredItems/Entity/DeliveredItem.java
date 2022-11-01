package com.csse.backend.RetrieveDeliveredItems.Entity;

//import com.csse.backend.domains.OrderItem;
import com.csse.backend.domains.Order;
import com.csse.backend.domains.User;
//import com.csse.backend.enums.SupplierOrderQuotationStatus;
import com.csse.backend.enums.ItemStatus;
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
@Table(name = "item")
public class DeliveredItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "user_id")
    private Long supplier;


    @Column(name = "order_id")
    private Long order;

    @Column(name = "deliverable_amount")
    private String deliverableAmount;

    @Column(name = "price_per_unit")
    private Float pricePerUnit;

    @Column(name = "deliverable_brand")
    private String deliverableBrand;

    @Column(name = "deliverable_date")
    private Date deliverableDate;

    @Column(name = "rejected_date")
    private Date rejectedDate;

    @Column(name = "rejected_reason")
    private String rejectedReason;

    @Column(name = "advice_note")
    private String adviceNote;

    @Column(name = "invoice")
    private String invoice;

    @Column(name = "item_status")
    @Enumerated(EnumType.ORDINAL)
    private ItemStatus itemStatus;


}
