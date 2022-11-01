package com.csse.backend.RetrieveDeliveredItems.Controller;

import com.csse.backend.RetrieveDeliveredItems.DTO.DeliveredItemResponseDTO;
import com.csse.backend.RetrieveDeliveredItems.DTO.ReportDTO;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.DeliveredItemsService;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deliveredItems")
@CrossOrigin("*")
public class DeliveredItemsController {

    final DeliveredItemsService deliveredItemsService;
    final ReportService reportService;

    public DeliveredItemsController(DeliveredItemsService deliveredItemsService, ReportService reportService) {
        this.deliveredItemsService = deliveredItemsService;
        this.reportService = reportService;
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<?> getAllOrders(){

        List<DeliveredItemResponseDTO> orderList = deliveredItemsService.getAllOrders();
        if(orderList.size()==0){
            return new ResponseEntity<>("No orders ", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderList, HttpStatus.OK);

    }

    @PostMapping("/saveReport")
    public ResponseEntity<?> saveReport(@RequestBody ReportDTO reportDTO){
        boolean status = reportService.saveReport(reportDTO);
        if(status){
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }
}
