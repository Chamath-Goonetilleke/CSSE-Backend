package com.csse.backend.RetrieveDeliveredItems.DTO;

import com.csse.backend.UserAndOrderManagement.domains.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDTO {
    private Long referenceNumber;
    private String item;
    private String quantity;
    private String brand;
    private Float unitPrice;
    private Float totalAmount;
    private Date deliveredDate;
    private String adviceNote;
    private User supplier;
}
