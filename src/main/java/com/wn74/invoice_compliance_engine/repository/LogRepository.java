package com.wn74.invoice_compliance_engine.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wn74.invoice_compliance_engine.domain.LogEntry;

public interface LogRepository extends JpaRepository<LogEntry, UUID> {

    public List<LogEntry> findByInvoiceIdOrderByLogDateDesc (
        UUID invoiceId
    );
    
}
