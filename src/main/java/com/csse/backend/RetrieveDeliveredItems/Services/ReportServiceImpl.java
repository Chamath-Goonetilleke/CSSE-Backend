package com.csse.backend.RetrieveDeliveredItems.Services;

import com.csse.backend.RetrieveDeliveredItems.DTO.ReportDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.Report;
import com.csse.backend.RetrieveDeliveredItems.Respository.ReportRepository;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReportServiceImpl implements ReportService {

    final
    ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }


    /**
     * Save test report
     */
    @Override
    public boolean saveReport(ReportDTO reportDTO) {
        try {
            Report report = new Report();
            report.setDate(reportDTO.getDate());
            report.setImage(reportDTO.getImage());

            Report report1 = reportRepository.saveReport(report);

            if (report1.getId() == null) {
                throw new NullPointerException();
            }
            return true;

        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
