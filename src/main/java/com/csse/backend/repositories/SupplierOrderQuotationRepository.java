package com.csse.backend.repositories;

import com.csse.backend.domains.Item;

import java.util.List;

public interface SupplierOrderQuotationRepository {

    Item getSupplierQuotationById(long supplierOrderQuotationId);

    Item saveSupplierOrderQuotation(Item item);

    List<Item> getAllCustomerAndSupplierAcceptedPurchaseRequisitions(long supplierId);

    List<Item> getAllCustomerAcceptedPurchaseRequisitions(long supplierId);

    List<Item> getAllCustomerApprovalPendingSoq(long employeeUserId);

}
