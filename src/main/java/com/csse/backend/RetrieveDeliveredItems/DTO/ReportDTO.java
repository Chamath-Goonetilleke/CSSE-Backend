package com.csse.backend.RetrieveDeliveredItems.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private Long id;
    private String date;
    private String image;
}
