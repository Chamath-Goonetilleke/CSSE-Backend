package com.csse.backend.UserAndOrderManagement.services;

import com.csse.backend.UserAndOrderManagement.domains.Item;
import com.csse.backend.UserAndOrderManagement.dtos.AcceptOrRejectPendingPr;
import com.csse.backend.UserAndOrderManagement.dtos.SupplierAcceptPrDto;

import java.util.List;

public interface SupplierOrderQuotationService {

    boolean acceptCustomerApprovedPurchaseRequisition(SupplierAcceptPrDto supplierAcceptPrDto);

    List<Item> getAllCustomerAndSupplierAcceptedPurchaseRequisitions(long supplierId);

    boolean updateAdviceNote(long supplierOrderQuotationId, String link);

    boolean updateInvoice(long supplierOrderQuotationId, String link);

    List<Item> getAllCustomerAcceptedPurchaseRequisitions(long supplierId);

    boolean acceptCustomerAcceptedOrderQuotation(long supplierOrderQuotationId);

    List<Item> getAllCustomerApprovalPendingSoq(long employeeUserId);

    boolean acceptOrRejectCustomerApprovalPendingSoq(AcceptOrRejectPendingPr command);

}
