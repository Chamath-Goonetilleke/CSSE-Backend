package com.csse.backend.UserAndOrderManagement.domains;

import com.csse.backend.UserAndOrderManagement.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_EMAIL_ADDRESS")
    String userEmailAddress;

    @Column(name = "USER_MOBILE_NUMBER")
    private String userMobileNumber;

    @Column(name = "USER_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private UserType userType;

    @Column(name = "USER_FIRST_AND_LAST_NAME")
    String userFirstAndLastName;

    @Column(name = "NIC")
    private String nic;

    @Column(name = "EMPLOYED_SITE_NAME")
    private String employedSiteName;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;

}
