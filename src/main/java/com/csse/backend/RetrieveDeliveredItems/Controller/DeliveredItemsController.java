package com.csse.backend.RetrieveDeliveredItems.Controller;

import com.csse.backend.RetrieveDeliveredItems.Entity.DeliveredItem;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.DeliveredItemsService;
import com.csse.backend.domains.SupplierOrderQuotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeliveredItemsController {

    final DeliveredItemsService deliveredItemsService;

    public DeliveredItemsController(DeliveredItemsService deliveredItemsService) {
        this.deliveredItemsService = deliveredItemsService;
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<?> getAllOrders(){

        List<DeliveredItem> orderList = deliveredItemsService.getAllOrders();
        if(orderList.size()==0){
            return new ResponseEntity<>("No orders ", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderList, HttpStatus.OK);

    }
}
