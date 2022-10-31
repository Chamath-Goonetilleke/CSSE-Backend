package com.csse.backend.RetrieveDeliveredItems.DTO;

import com.csse.backend.domains.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveredItemResponseDTO {

    private ReceiptDTO details;

}
