package com.csse.backend.repositories;

import com.csse.backend.domains.SupplierOrderQuotation;

import java.util.List;

public interface SupplierOrderQuotationRepository {

    SupplierOrderQuotation getSupplierQuotationById(long supplierOrderQuotationId);

    SupplierOrderQuotation saveSupplierOrderQuotation(SupplierOrderQuotation supplierOrderQuotation);

    List<SupplierOrderQuotation> getAllCustomerAndSupplierAcceptedPurchaseRequisitions(long supplierId);

    List<SupplierOrderQuotation> getAllCustomerAcceptedPurchaseRequisitions(long supplierId);

    List<SupplierOrderQuotation> getAllCustomerApprovalPendingSoq(long employeeUserId);

}
