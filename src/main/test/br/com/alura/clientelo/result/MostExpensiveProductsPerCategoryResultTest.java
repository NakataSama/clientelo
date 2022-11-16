package br.com.alura.clientelo.result;

import br.com.alura.clientelo.fileprocessor.FileProcessor;
import br.com.alura.clientelo.report.ReportOrderDTO;
import br.com.alura.clientelo.mock.ResultMocks;
import br.com.alura.clientelo.report.impl.MostExpensiveProductsPerCategory;
import br.com.alura.clientelo.report.result.impl.MostExpensiveProductsPerCategoryResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MostExpensiveProductsPerCategoryResultTest {

    private MostExpensiveProductsPerCategoryResult subject;
    private MostExpensiveProductsPerCategory report;
    private List<ReportOrderDTO> orders;
    private String filePath;

    @BeforeEach
    public void setUp() throws Exception {
        report = new MostExpensiveProductsPerCategory();
        filePath = "pedidos.json";
        orders = new FileProcessor().processFile(filePath);
    }

    @Test
    public void shouldGenerateText() {
        subject = (MostExpensiveProductsPerCategoryResult) report.process(orders, null);
        assertEquals(ResultMocks.getMostExpensiveProductsPerCategoryReportMock(), subject.generateText());
    }

    @Test
    public void shouldThrowExceptionWhenResultIsInvalid() {
        assertThrows(RuntimeException.class, () -> subject.generateText());
    }
}
