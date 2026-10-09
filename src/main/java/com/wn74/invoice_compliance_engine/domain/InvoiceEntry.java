package com.wn74.invoice_compliance_engine.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import jakarta.persistence.*;

//TODO

// [] NULL HANDLING
// [] JPA ANNOTATIONS

@Entity
@Table(name = "invoiceEntries")

public class InvoiceEntry{
    private static final int SCALE_INTERMEDIATE = 4;
    private static final int SCALE_FINAL = 2;
    private static final RoundingMode ROUNDING = RoundingMode.HALF_UP;

    @Id
    private UUID entryId = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id") 
    private Invoice invoice;
    private String productName;
    
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    private TaxRate taxRate;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalNetAmount;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalGrossAmount;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal taxAmount;

    

    //Empty constructor for frameworks
    public InvoiceEntry(){

    }
    //Parameterized constructor for logic
    public InvoiceEntry(String productName, BigDecimal quantity, BigDecimal unitPrice, TaxRate taxRate) {
    this.productName = productName;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.taxRate = taxRate;
    recalculateEverything(); 
    }

    public BigDecimal calculateTotalNetAmount(){

        BigDecimal intermediate = this.quantity.multiply(this.unitPrice).setScale(SCALE_INTERMEDIATE, ROUNDING);

        this.totalNetAmount = intermediate.setScale(SCALE_FINAL, ROUNDING);

        return  totalNetAmount;

    }

    public BigDecimal calculateTaxAmount(BigDecimal totalNetAmount){

        BigDecimal intermediate = totalNetAmount.multiply(this.taxRate.getRate()).setScale(SCALE_INTERMEDIATE, ROUNDING);

        this.taxAmount = intermediate.setScale(SCALE_FINAL, ROUNDING);

        return taxAmount;

    }

    public BigDecimal calculateTotalGrossAmount(BigDecimal totalNetAmount, BigDecimal taxAmount){

        
        BigDecimal intermediate = totalNetAmount.add(taxAmount).setScale(SCALE_INTERMEDIATE, ROUNDING);
        
        this.totalGrossAmount = intermediate.setScale(SCALE_FINAL, ROUNDING);       

        return  totalGrossAmount;

    }

    

    public void recalculateEverything(){

        this.totalNetAmount = calculateTotalNetAmount();
        this.taxAmount = calculateTaxAmount(totalNetAmount);
        this.totalGrossAmount = calculateTotalGrossAmount(totalNetAmount, taxAmount);
        
    }


    public UUID getEntryId() {
    return entryId;
   }
 
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
        recalculateEverything();
    }

    public TaxRate getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(TaxRate taxRate) {
        this.taxRate = taxRate;
        recalculateEverything();
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        recalculateEverything();
    }

    public BigDecimal getTotalNetAmount() {
        return totalNetAmount;
    }

    public BigDecimal getTotalGrossAmount() {
        return totalGrossAmount;
    }
    
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }
    public Invoice getInvoice() {
        return invoice;
    }
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((entryId == null) ? 0 : entryId.hashCode());
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
        InvoiceEntry other = (InvoiceEntry) obj;
        if (entryId == null) {
            if (other.entryId != null)
                return false;
        } else if (!entryId.equals(other.entryId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "InvoiceEntry [entryId=" + entryId + ", productName=" + productName + ", quantity=" + quantity
                + ", taxRate=" + taxRate + ", unitPrice=" + unitPrice + ", totalNetAmount=" + totalNetAmount
                + ", totalGrossAmount=" + totalGrossAmount + ", taxAmount=" + taxAmount + "]";
    }

    
    
   

}
