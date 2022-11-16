package br.com.alura.clientelo.result;

import br.com.alura.clientelo.fileprocessor.FileProcessor;
import br.com.alura.clientelo.report.ReportOrderDTO;
import br.com.alura.clientelo.mock.ResultMocks;
import br.com.alura.clientelo.report.impl.MostProfitableCustomers;
import br.com.alura.clientelo.report.result.impl.MostProfitableCustomersResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MostProfitableCustomersResultTest {

    private MostProfitableCustomersResult subject;
    private MostProfitableCustomers report;
    private List<ReportOrderDTO> orders;
    private String filePath;

    @BeforeEach
    public void setUp() throws Exception {
        report = new MostProfitableCustomers();
        filePath = "pedidos.json";
        orders = new FileProcessor().processFile(filePath);
    }

    @Test
    public void shouldGenerateText() {
        subject = (MostProfitableCustomersResult) report.process(orders, null);
        assertEquals(ResultMocks.getMostProfitableCustomersReportMock(), subject.generateText());
    }

    @Test
    public void shouldThrowExceptionWhenResultIsInvalid() {
        assertThrows(RuntimeException.class, () -> subject.generateText());
    }
}
