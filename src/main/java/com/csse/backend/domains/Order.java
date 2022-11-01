package com.csse.backend.domains;

import com.csse.backend.enums.OrderStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ORDER_ITEM")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable = false)
    @JsonIgnore
    private User createdBy;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Item> items;

    @Column(name = "ORDER_ITEM_NAME")
    private String orderItemName;

    @Column(name = "ORDER_REQUIRED_DATE")
    private Date orderRequiredDate;

    @Column(name = "ORDER_REQUIRED_AMOUNT")
    private String orderRequiredAmount;

    @Column(name = "ORDER_ITEM_DELIVERY_ADDRESS")
    private String orderItemDeliveryAddress;

    @Column(name = "ORDER_STATUS")
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

}
