package br.com.alura.clientelo.result;

import br.com.alura.clientelo.fileprocessor.FileProcessor;
import br.com.alura.clientelo.report.ReportOrderDTO;
import br.com.alura.clientelo.mock.ResultMocks;
import br.com.alura.clientelo.report.impl.SalesPerCategory;
import br.com.alura.clientelo.report.result.impl.SalesPerCategoryResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SalesPerCategoryResultTest {

    private SalesPerCategoryResult subject;
    private SalesPerCategory report;
    private List<ReportOrderDTO> orders;
    private String filePath;

    @BeforeEach
    public void setUp() throws Exception {
        report = new SalesPerCategory();
        filePath = "pedidos.json";
        orders = new FileProcessor().processFile(filePath);
    }

    @Test
    public void shouldGenerateText() {
        subject = (SalesPerCategoryResult) report.process(orders, null);
        assertEquals(ResultMocks.getSalesPerCategoryReportMock(), subject.generateText());
    }

    @Test
    public void shouldThrowExceptionWhenResultIsInvalid() {
        assertThrows(RuntimeException.class, () -> subject.generateText());
    }
}
