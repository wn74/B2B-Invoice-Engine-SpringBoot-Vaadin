package com.wn74.invoice_compliance_engine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wn74.invoice_compliance_engine.domain.Invoice;
import com.wn74.invoice_compliance_engine.repository.InvoiceRepository;


@Component 
public class InvoiceValidator {

    private final InvoiceRepository invoiceRepository;

    public InvoiceValidator(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public record ValidationResult(boolean isValid, List<String> errors) {
        public static  ValidationResult valid(){

            return new ValidationResult(true,List.of()); 

        }   
        
        public static ValidationResult invalid(List<String> errors){

            return new ValidationResult(false,errors);

        }       

    }

    public ValidationResult validate(Invoice invoice){

        List<String> errors = new ArrayList<>();

        checkArithmetic(invoice, errors);
        checkDates(invoice, errors);
        checkTaxId(invoice, errors);
        checkDuplicates(invoice, errors);

        return errors.isEmpty() ? ValidationResult.valid() : ValidationResult.invalid(errors);

    }


    
    private void checkArithmetic(Invoice invoice, List<String> errors){
        //check Empty list of invoiceEntries
        //check not null and positive quantity and UnitPrice
        //use compareTo
        //list(subtotal) + list(taxAmount) = grandtotal
        //list(taxAmount) = taxTotal
        //list(subtotal) = net amount


    }

    private void checkDates(Invoice invoice, List<String> errors) {



    }

    private void checkTaxId(Invoice invoice, List<String> errors) {



    }

    private void checkDuplicates(Invoice invoice, List<String> errors) {



    }



}