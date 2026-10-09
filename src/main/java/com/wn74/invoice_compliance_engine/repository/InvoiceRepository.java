package com.wn74.invoice_compliance_engine.repository;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InvoiceRepository extends JpaRepository, JpaSpecificationExecutor{

    
    public boolean existsBySupplierTaxIdAndInvoiceNumberAndIssueDateAfter (
        String supplierTaxId,
        String invoiceNumber,
        LocalDate cutoffDate
    );

}
