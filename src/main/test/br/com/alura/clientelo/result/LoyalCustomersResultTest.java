package br.com.alura.clientelo.result;

import br.com.alura.clientelo.reportgenerator.fileprocessor.FileProcessor;
import br.com.alura.clientelo.reportgenerator.report.ReportOrderDTO;
import br.com.alura.clientelo.mock.ResultMocks;
import br.com.alura.clientelo.reportgenerator.report.impl.LoyalCustomers;
import br.com.alura.clientelo.reportgenerator.report.result.impl.LoyalCustomersResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoyalCustomersResultTest {

    private LoyalCustomersResult subject;
    private LoyalCustomers report;
    private List<ReportOrderDTO> orders;
    private String filePath;

    @BeforeEach
    public void setUp() throws Exception {
        report = new LoyalCustomers();
        filePath = "pedidos.json";
        orders = new FileProcessor().processFile(filePath);
    }

    @Test
    public void shouldGenerateText() {
        subject = (LoyalCustomersResult) report.process(orders, null);
        assertEquals(ResultMocks.getLoyalCustomersReportMock(), subject.generateText());
    }

    @Test
    public void shouldThrowExceptionWhenResultIsInvalid() {
        assertThrows(RuntimeException.class, () -> subject.generateText());
    }
}
