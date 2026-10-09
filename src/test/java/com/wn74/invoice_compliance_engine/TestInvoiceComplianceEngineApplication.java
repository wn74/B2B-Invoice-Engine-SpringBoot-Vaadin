package com.wn74.invoice_compliance_engine;

import org.springframework.boot.SpringApplication;

public class TestInvoiceComplianceEngineApplication {

	public static void main(String[] args) {
		SpringApplication.from(InvoiceComplianceEngineApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
