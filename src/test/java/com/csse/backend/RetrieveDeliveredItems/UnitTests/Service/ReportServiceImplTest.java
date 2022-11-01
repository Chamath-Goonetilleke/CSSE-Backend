package com.csse.backend.RetrieveDeliveredItems.UnitTests.Service;

import com.csse.backend.RetrieveDeliveredItems.DTO.ReportDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.Report;
import com.csse.backend.RetrieveDeliveredItems.Respository.ReportRepository;
import com.csse.backend.RetrieveDeliveredItems.Services.ReportServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReportServiceImplTest {

    @Autowired
    ReportServiceImpl reportService;

    @MockBean
    ReportRepository reportRepository;

    @Test
    @DisplayName("SaveReportSuccess")
    void TestSaveReportSuccess(){
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setDate("01-10-2022");
        reportDTO.setImage("ImageUrl");

        Mockito.when(reportRepository.saveReport(Mockito.any())).thenReturn(new Report(1L,"01-10-2022","ImageUrl"));

        boolean result = reportService.saveReport(reportDTO);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("SaveReportFailure")
    void TestSaveReportFailure(){
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setDate("01-10-2022");
        reportDTO.setImage("ImageUrl");

        Mockito.when(reportRepository.saveReport(Mockito.any())).thenReturn(new Report());

        boolean result = reportService.saveReport(reportDTO);
        Assertions.assertFalse(result);
    }



}
