package br.com.alura.clientelo.report;

import br.com.alura.clientelo.reportgenerator.infra.fileprocessor.FileProcessor;
import br.com.alura.clientelo.reportgenerator.domain.report.dto.ReportOrderDTO;
import br.com.alura.clientelo.reportgenerator.domain.report.impl.MostProfitableCustomers;
import br.com.alura.clientelo.reportgenerator.domain.result.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MostProfitableCustomersTest {

    private MostProfitableCustomers subject;
    private List<ReportOrderDTO> orders;
    private String filePath;

    @BeforeEach
    public void setUp() throws Exception {
        subject = new MostProfitableCustomers();
        filePath = "pedidos.json";
        orders = new FileProcessor().processFile(filePath);
    }

    @Test
    public void shouldProcess() {
        Result result = subject.process(orders, null);
        assertInstanceOf(result.getClass().getClass(), MostProfitableCustomers.class);
    }

    @Test
    public void shouldThrowExceptionWhenReportIsInvalid() {
        assertThrows(RuntimeException.class, () -> subject.process(null, null));
    }
}
