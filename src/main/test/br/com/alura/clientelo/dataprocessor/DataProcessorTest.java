package br.com.alura.clientelo.dataprocessor;

import br.com.alura.clientelo.store.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DataProcessorTest {

    private DataProcessor subject;
    private String filePath;

    @BeforeEach
    public void setUp() {
        subject = new DataProcessor();
        filePath = "pedidos.json";
    }

    @Test
    public void shouldProcessData() {
        List<Order> orders = subject.processFile(filePath);
        assertEquals(orders.size(), 2);
    }

    @Test
    public void shouldThrowExceptionWhenFileNotFound() {
        filePath = "/test.fail";
        assertThrows(RuntimeException.class, () -> subject.processFile(filePath));
    }

    @Test
    public void shouldThrowExceptionWhenConvertingFile() {
        filePath = "pedidos.csv";
        assertThrows(RuntimeException.class, () -> subject.processFile(filePath));
    }
}
