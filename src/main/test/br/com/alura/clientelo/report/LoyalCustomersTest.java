package br.com.alura.clientelo.report;

import br.com.alura.clientelo.fileprocessor.FileProcessor;
import br.com.alura.clientelo.report.impl.LoyalCustomers;
import br.com.alura.clientelo.report.result.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoyalCustomersTest {

    private LoyalCustomers subject;
    private List<ReportOrderDTO> orders;
    private String filePath;

    @BeforeEach
    public void setUp() throws Exception {
        subject = new LoyalCustomers();
        filePath = "pedidos.json";
        orders = new FileProcessor().processFile(filePath);
    }

    @Test
    public void shouldProcess() {
        Result result = subject.process(orders, null);
        assertInstanceOf(result.getClass().getClass(), LoyalCustomers.class);
    }

    @Test
    public void shouldThrowExceptionWhenReportIsInvalid() {
        assertThrows(RuntimeException.class, () -> subject.process(null, null));
    }
}
