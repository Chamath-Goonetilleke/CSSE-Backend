package com.csse.backend.RetrieveDeliveredItems.Controller;

import com.csse.backend.RetrieveDeliveredItems.Common.CommonConstants;
import com.csse.backend.RetrieveDeliveredItems.DTO.DeliveredItemResponseDTO;
import com.csse.backend.RetrieveDeliveredItems.DTO.ReportDTO;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.DeliveredItemsService;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CommonConstants.DELIVERED_ITEMS_BASE_URL)
public class DeliveredItemsController {

    final DeliveredItemsService deliveredItemsService;
    final ReportService reportService;

    public DeliveredItemsController(DeliveredItemsService deliveredItemsService, ReportService reportService) {
        this.deliveredItemsService = deliveredItemsService;
        this.reportService = reportService;
    }

    /**
     * API for get All delivered items
     */
    @GetMapping(CommonConstants.GET_ALL_ORDERS)
    public ResponseEntity<?> getAllOrders() {

        List<DeliveredItemResponseDTO> orderList = deliveredItemsService.getAllOrders();
        if (orderList.size() == 0) {
            return new ResponseEntity<>(CommonConstants.NO_DATA_FOUND, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderList, HttpStatus.OK);

    }

    /**
     * API for save test reports
     */
    @PostMapping(CommonConstants.SAVE_REPORT)
    public ResponseEntity<?> saveReport(@RequestBody ReportDTO reportDTO) {
        boolean status = reportService.saveReport(reportDTO);
        if (status) {
            return new ResponseEntity<>(CommonConstants.SAVED, HttpStatus.OK);
        }
        return new ResponseEntity<>(CommonConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }
}
