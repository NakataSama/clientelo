package br.com.alura.clientelo.result;

import br.com.alura.clientelo.dataprocessor.DataProcessor;
import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.report.impl.TopSellingProducts;
import br.com.alura.clientelo.mock.ResultMocks;
import br.com.alura.clientelo.report.result.impl.TopSellingProductsResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TopSellingProductsResultTest {

    private TopSellingProductsResult subject;
    private TopSellingProducts report;
    private List<Order> orders;
    private String filePath;

    @BeforeEach
    public void setUp() throws Exception {
        report = new TopSellingProducts();
        filePath = "./test/pedidos.json";
        orders = new DataProcessor().processFile(filePath);
    }

    @Test
    public void shouldGenerateText() {
        subject = (TopSellingProductsResult) report.process(orders, null);
        assertEquals(ResultMocks.getTopSellingProductsReportMock(), subject.generateText());
    }

    @Test
    public void shouldThrowExceptionWhenResultIsInvalid() {
        assertThrows(RuntimeException.class, () -> subject.generateText());
    }
}
