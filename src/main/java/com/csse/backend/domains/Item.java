package com.csse.backend.domains;

import com.csse.backend.enums.ItemStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable = false)
    @JsonIgnore
    private User supplier;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @Column(name = "DELIVERABLE_AMOUNT")
    private String deliverableAmount;

    @Column(name = "PRICE_PER_UNIT")
    private Float pricePerUnit;

    @Column(name = "DELIVERABLE_BRAND")
    private String deliverableBrand;

    @Column(name = "DELIVERABLE_DATE")
    private Date deliverableDate;

    @Column(name = "REJECTED_DATE")
    private Date rejectedDate;

    @Column(name = "REJECTED_REASON")
    private String rejectedReason;

    @Column(name = "ADVICE_NOTE")
    private String adviceNote;

    @Column(name = "INVOICE")
    private String invoice;

    @Column(name = "ITEM_STATUS")
    @Enumerated(EnumType.ORDINAL)
    private ItemStatus itemStatus;

}
