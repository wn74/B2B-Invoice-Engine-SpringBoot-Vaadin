package com.wn74.invoice_compliance_engine.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wn74.invoice_compliance_engine.domain.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID>, JpaSpecificationExecutor<Invoice>{

    
    public boolean existsBySupplierTaxIdAndInvoiceNumberAndIssueDateAfterAndInvoiceIdNot (
        String supplierTaxId,
        String invoiceNumber,
        LocalDate cutoffDate,
        UUID invoiceId
    );

}
