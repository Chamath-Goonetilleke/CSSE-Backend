package com.csse.backend.RetrieveDeliveredItems.Respository.Impl;

import com.csse.backend.RetrieveDeliveredItems.Entity.Report;
import com.csse.backend.RetrieveDeliveredItems.Respository.ReportRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
@Repository
public class ReportRepositoryImpl implements ReportRepository {

    final
    EntityManager entityManager;

    public ReportRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveReport(Report report) {
        entityManager.persist(report);
    }
}
