package com.wn74.invoice_compliance_engine.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;



public class Invoice {

    private UUID invoiceId = UUID.randomUUID();
    private String invoiceNumber;
    private String supplierName;
    private String supplierTaxId;
    private String customerName;
    
    private Currency currency; 

    private LocalDate issueDate = LocalDate.now();
    private LocalDate dueDate;

    
    private InvoiceStatus status = InvoiceStatus.DRAFT;
    private List<InvoiceEntry> invoiceEntries = new ArrayList<>();

    private  BigDecimal subtotal;
    private  BigDecimal totalTax;
    private  BigDecimal grandTotal;

    public Invoice(){}

    public Invoice(String invoiceNumber,Currency currency,String supplierName, String supplierTaxId, String customerName,
            LocalDate dueDate) {
        this.supplierName = supplierName;
        this.supplierTaxId = supplierTaxId;
        this.customerName = customerName;
        this.currency = currency;
        this.dueDate = dueDate;
        this.invoiceNumber = invoiceNumber;
    }

    public void addEntry(InvoiceEntry entry){
        this.invoiceEntries.add(entry);
        entry.setInvoice(this);
        recalculateTotals();
    }
    public void removeEntry(InvoiceEntry entry){
        this.invoiceEntries.remove(entry);
        entry.setInvoice(null);
        recalculateTotals();
    }
    public void recalculateTotals(){
        BigDecimal calculatedSubtotal = BigDecimal.ZERO.setScale(2);
        BigDecimal calculatedTax = BigDecimal.ZERO.setScale(2);
        this.grandTotal = BigDecimal.ZERO.setScale(2);
        

        for (InvoiceEntry entry : this.invoiceEntries) {
            if (entry.getTotalNetAmount() != null) {
                calculatedSubtotal = calculatedSubtotal.add(entry.getTotalNetAmount()).setScale(2);
            }
            if (entry.getTaxAmount() != null) {
                calculatedTax = calculatedTax.add(entry.getTaxAmount()).setScale(2);
            }
            

        }

        this.subtotal = calculatedSubtotal.setScale(2);
        this.totalTax = calculatedTax.setScale(2);
        this.grandTotal = calculatedSubtotal.add(calculatedTax).setScale(2);

    }


    public UUID getInvoiceId() {
        return invoiceId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierTaxId() {
        return supplierTaxId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public List<InvoiceEntry> getInvoiceEntries() {
        return invoiceEntries;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setSupplierTaxId(String supplierTaxId) {
        this.supplierTaxId = supplierTaxId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Invoice other = (Invoice) obj;
        if (invoiceId == null) {
            if (other.invoiceId != null)
                return false;
        } else if (!invoiceId.equals(other.invoiceId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Invoice [invoiceId=" + invoiceId + ", invoiceNumber=" + invoiceNumber + ", supplierName=" + supplierName
                + ", supplierTaxId=" + supplierTaxId + ", customerName=" + customerName + ", currency=" + currency
                + ", issueDate=" + issueDate + ", dueDate=" + dueDate + ", status=" + status + ", invoiceEntries="
                + invoiceEntries + ", subtotal=" + subtotal + ", totalTax=" + totalTax + ", grandTotal=" + grandTotal
                + "]";
    }

    
    
}
