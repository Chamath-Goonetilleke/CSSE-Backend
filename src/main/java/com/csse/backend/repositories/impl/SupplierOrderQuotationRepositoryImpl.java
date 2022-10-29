package com.csse.backend.repositories.impl;

import com.csse.backend.domains.SupplierOrderQuotation;
import com.csse.backend.repositories.SupplierOrderQuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class SupplierOrderQuotationRepositoryImpl implements SupplierOrderQuotationRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public SupplierOrderQuotation getSupplierQuotationById(long supplierOrderQuotationId) {
        return entityManager.find(SupplierOrderQuotation.class, supplierOrderQuotationId);
    }

    @Override
    @Transactional
    public SupplierOrderQuotation saveSupplierOrderQuotation(SupplierOrderQuotation supplierOrderQuotation) {
        if (supplierOrderQuotation.getId() == null) {
            entityManager.persist(supplierOrderQuotation);
        } else {
            supplierOrderQuotation = entityManager.merge(supplierOrderQuotation);
        }

        return supplierOrderQuotation;
    }

    @Override
    public List<SupplierOrderQuotation> getAllCustomerAndSupplierAcceptedPurchaseRequisitions(long supplierId) {
        TypedQuery<SupplierOrderQuotation> query = entityManager
                .createQuery("SELECT s FROM SupplierOrderQuotation s, OrderItem o WHERE s.supplier.id = :supplierId AND s.supplierOrderQuotationStatus = 2", SupplierOrderQuotation.class);
        query.setParameter("supplierId", supplierId);
        return query.getResultList();
    }

    @Override
    public List<SupplierOrderQuotation> getAllCustomerAcceptedPurchaseRequisitions(long supplierId) {
        TypedQuery<SupplierOrderQuotation> query = entityManager
                .createQuery("SELECT s FROM SupplierOrderQuotation s WHERE s.supplier.id = :supplierId AND s.supplierOrderQuotationStatus = 1", SupplierOrderQuotation.class);
        query.setParameter("supplierId", supplierId);
        return query.getResultList();
    }

    @Override
    public List<SupplierOrderQuotation> getAllCustomerApprovalPendingSoq(long employeeUserId) {
        TypedQuery<SupplierOrderQuotation> query = entityManager
                .createQuery("SELECT s FROM SupplierOrderQuotation s WHERE s.orderItem.createdBy.id = :employeeUserId AND s.supplierOrderQuotationStatus = 0", SupplierOrderQuotation.class);
        query.setParameter("employeeUserId", employeeUserId);
        return query.getResultList();
    }

}
