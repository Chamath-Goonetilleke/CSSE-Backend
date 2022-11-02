package com.csse.backend.ItemServicesTest.Item;

import com.csse.backend.UserAndOrderManagement.dtos.SupplierAcceptPrDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemServiceTests {

    @Autowired
    private MockMvc mockMvc;

    /*
        Test whether a supervisor approved purchase requisition can be accepted with a supplier identification
     */
    @Test
    public void acceptCustomerApprovedPurchaseRequisitionReturnsHttpStatusCreated() throws Exception {
        SupplierAcceptPrDto dto = new SupplierAcceptPrDto();
        dto.setSupplierId(1L);
        dto.setOrderItemId(3L);
        dto.setQuantity("0.75 cubes");
        dto.setUnitPrice(2300F);
        dto.setBrand("Imported");
        dto.setDateCanDeliver(new Date(2020, 10, 10));

        String jsonBody = new ObjectMapper().writeValueAsString(dto);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/supplier/accept-customer-approved-pr")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isCreated());
    }

    /*
        Test whether a supervisor approved purchase requisition cannot be accepted without a supplier identification
     */
    @Test
    public void acceptCustomerApprovedPurchaseRequisitionReturnsHttpStatusConflict() throws Exception {
        SupplierAcceptPrDto dto = new SupplierAcceptPrDto();
        dto.setOrderItemId(30L);
        dto.setQuantity("0.75 cubes");
        dto.setUnitPrice(2300F);
        dto.setBrand("Imported");
        dto.setDateCanDeliver(new Date(2020, 10, 10));

        String jsonBody = new ObjectMapper().writeValueAsString(dto);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/supplier/accept-customer-approved-pr")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isConflict());
    }

    /*
        Test whether all ready to deliver items can be get by an existing supplier identification
     */
    @Test
    public void getAllCustomerAndSupplierAcceptedPurchaseRequisitionsReturnsStatusOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/supplier/all-customer-supplier-accepted-pr/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());
    }

    /*
        Test whether all ready to deliver items cannot be get by a non-existing supplier identification
     */
    @Test
    public void getAllCustomerAndSupplierAcceptedPurchaseRequisitionsReturnsStatusNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/supplier/all-customer-supplier-accepted-pr/10")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isNotFound());
    }

    /*
        Test whether an advice note can be updated for an existing supplier order quotation
     */
    @Test
    public void updateAdviceNoteReturnsStatusAccepted() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/supplier/update-advice-note/1/1&1029232")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isAccepted());
    }

    /*
        Test whether an advice note cannot be updated for a non-existing supplier order quotation
     */
    @Test
    public void updateAdviceNoteReturnsStatusUnprocessableEntity() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/supplier/update-advice-note/100/1&1029232")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isUnprocessableEntity());
    }

    /*
        Test whether an invoice can be updated for an existing supplier order quotation
    */
    @Test
    public void updateInvoiceReturnsHttpStatusAccepted() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/supplier/update-invoice/1/1&1029232")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isAccepted());
    }

    /*
        Test whether an invoice cannot be updated for a non-existing supplier order quotation
    */
    @Test
    public void updateInvoiceReturnsHttpStatusAUnprocessableEntity() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/supplier/update-invoice/100/1&1029232")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isUnprocessableEntity());
    }

    /*
        Test whether all supervisor accepted purchase requisitions can be retrieved
        using supplier identification
     */
    @Test
    public void getAllCustomerAcceptedPurchaseRequisitionsReturnsOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/supplier/all-customer-accepted-pr/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());
    }

    /*
        Test whether all supervisor accepted purchase requisitions can be retrieved
        using supplier identification
    */
    @Test
    public void getAllCustomerAcceptedPurchaseRequisitionsReturnsNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/supplier/all-customer-accepted-pr/100")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isNotFound());
    }

    /*
        Test whether all supplier accepted and waiting for supervisor response, purchase requisitions requests
        can be retrieved using supervisor identification
     */
    @Test
    public void getAllCustomerApprovalPendingSoqReturnsOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/supplier/all-customer-approval-pending-soq/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());
    }

    /*
        Test whether all supplier accepted and waiting for supervisor response, purchase requisitions requests
        cannot be retrieved using an invalid supervisor identification
    */
    @Test
    public void getAllCustomerApprovalPendingSoqReturnsNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/supplier/all-customer-approval-pending-soq/100")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isNotFound());
    }

}
