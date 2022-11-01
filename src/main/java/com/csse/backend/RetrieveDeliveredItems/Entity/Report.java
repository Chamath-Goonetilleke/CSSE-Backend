package com.csse.backend.RetrieveDeliveredItems.Entity;

import com.csse.backend.RetrieveDeliveredItems.Common.CommonConstants;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = CommonConstants.REPORT_DB_NAME)
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = CommonConstants.REPORT_ID)
    private Long id;

    @Column(name = CommonConstants.REPORT_DATE)
    private String date;

    @Column(name = CommonConstants.REPORT_IMAGE)
    private String image;
}
