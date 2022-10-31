package com.csse.backend.UserAndOrderManagement.services;

import com.csse.backend.UserAndOrderManagement.domains.SupplierOrderQuotation;
import com.csse.backend.UserAndOrderManagement.dtos.AcceptOrRejectPendingPr;
import com.csse.backend.UserAndOrderManagement.dtos.SupplierAcceptPrDto;

import java.util.List;

public interface SupplierOrderQuotationService {

    boolean acceptCustomerApprovedPurchaseRequisition(SupplierAcceptPrDto supplierAcceptPrDto);

    List<SupplierOrderQuotation> getAllCustomerAndSupplierAcceptedPurchaseRequisitions(long supplierId);

    boolean updateAdviceNote(long supplierOrderQuotationId, String link);

    boolean updateInvoice(long supplierOrderQuotationId, String link);

    List<SupplierOrderQuotation> getAllCustomerAcceptedPurchaseRequisitions(long supplierId);

    boolean acceptCustomerAcceptedOrderQuotation(long supplierOrderQuotationId);

    List<SupplierOrderQuotation> getAllCustomerApprovalPendingSoq(long employeeUserId);

    boolean acceptOrRejectCustomerApprovalPendingSoq(AcceptOrRejectPendingPr command);

}
