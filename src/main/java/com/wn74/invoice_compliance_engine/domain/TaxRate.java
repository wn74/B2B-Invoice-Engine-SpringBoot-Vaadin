package com.wn74.invoice_compliance_engine.domain;

import java.math.BigDecimal;


public enum TaxRate {
    
    TAX_0(new BigDecimal("0.00")),
    TAX_7(new BigDecimal("0.07")) ,
    TAX_19(new BigDecimal("0.19"));

    private final BigDecimal rate;

    TaxRate(BigDecimal rate){
        this.rate = rate;
    }

    public BigDecimal getRate(){
        return rate;
    }
}

