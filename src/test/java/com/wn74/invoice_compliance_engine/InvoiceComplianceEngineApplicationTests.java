package com.wn74.invoice_compliance_engine;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class InvoiceComplianceEngineApplicationTests {

	@Test
	void contextLoads() {
	}

}
