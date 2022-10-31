package com.csse.backend.RetrieveDeliveredItems.Respository;

import com.csse.backend.RetrieveDeliveredItems.DTO.ReportDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.Report;

public interface ReportRepository {

    void saveReport(Report report);
}
