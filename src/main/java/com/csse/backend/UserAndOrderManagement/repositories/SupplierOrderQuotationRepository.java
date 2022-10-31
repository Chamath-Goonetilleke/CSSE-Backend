package com.csse.backend.UserAndOrderManagement.repositories;

import com.csse.backend.UserAndOrderManagement.domains.SupplierOrderQuotation;

import java.util.List;

public interface SupplierOrderQuotationRepository {

    SupplierOrderQuotation getSupplierQuotationById(long supplierOrderQuotationId);

    SupplierOrderQuotation saveSupplierOrderQuotation(SupplierOrderQuotation supplierOrderQuotation);

    List<SupplierOrderQuotation> getAllCustomerAndSupplierAcceptedPurchaseRequisitions(long supplierId);

    List<SupplierOrderQuotation> getAllCustomerAcceptedPurchaseRequisitions(long supplierId);

    List<SupplierOrderQuotation> getAllCustomerApprovalPendingSoq(long employeeUserId);

}
