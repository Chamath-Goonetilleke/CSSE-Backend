package com.csse.backend.RetrieveDeliveredItems.Services;

import com.csse.backend.RetrieveDeliveredItems.DTO.ReportDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.Report;
import com.csse.backend.RetrieveDeliveredItems.Respository.ReportRepository;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.ReportService;
import org.springframework.stereotype.Component;

@Component
public class ReportServiceImpl implements ReportService {

    final
    ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public boolean saveReport(ReportDTO reportDTO) {
       try {
           Report report = new Report();
           report.setDate(reportDTO.getDate());
           report.setImage(reportDTO.getImage());

           reportRepository.saveReport(report);

           return true;

       }catch (Exception e){
           return false;
       }
    }
}