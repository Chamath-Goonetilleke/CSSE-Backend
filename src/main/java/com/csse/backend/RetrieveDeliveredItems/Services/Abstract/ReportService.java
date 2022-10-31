package com.csse.backend.RetrieveDeliveredItems.Services.Abstract;

import com.csse.backend.RetrieveDeliveredItems.DTO.ReportDTO;

public interface ReportService {

    boolean saveReport(ReportDTO reportDTO);
}
