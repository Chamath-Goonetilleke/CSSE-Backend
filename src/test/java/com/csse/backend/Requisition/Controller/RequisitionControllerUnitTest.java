package com.csse.backend.Requisition.Controller;

import com.csse.backend.Requisition.Dto.OrderDto;
import com.csse.backend.Requisition.Repository.RequisitionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Controller
public class RequisitionControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private RequisitionRepository requisitionRepository;

    @Test
    public void checkGivenOrder_whenGetOrder_thenStatus200()
            throws Exception {

        OrderDto orderDto = new OrderDto();
        orderDto.setSiteManagerName("kavindu");
        orderDto.setDeliveryAddress("Galle, Road");
        orderDto.setNotes("120 cube");
        orderDto.setQuantity(45);



        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/requisition/add-new")
                .content(new ObjectMapper().writeValueAsString(orderDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isCreated());


    }

//    Cannot enter Requisition Status
    @Test
    public void check_Given_Order_when_Get_Order_then_Status()
            throws Exception {

        OrderDto orderDto = new OrderDto();
        orderDto.setNotes("120 cube");
        orderDto.setDeliveryAddress("Road");
        orderDto.setSiteManagerName("Madushan");
        orderDto.setStatus("Pending");
        orderDto.setNotes("120 cube");
        orderDto.setQuantity(5);



        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/requisition/add-new")
                .content(new ObjectMapper().writeValueAsString(orderDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isConflict());


    }

    @Test
    public void check_Pending_Requisitions_Returns_Status200() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/requisition/get-pending-orders")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void check_Approved_Requisitions_Returns_Status200() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/requisition/get-approved-requisitions")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void check_Approved_Requisitions_Returns_Status400() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/requisition/get-approved-requisitions/{name}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isNotFound());
    }
}
