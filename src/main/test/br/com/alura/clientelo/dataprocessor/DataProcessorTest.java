package br.com.alura.clientelo.dataprocessor;

import br.com.alura.clientelo.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DataProcessorTest {

    private DataProcessor subject;
    private String filePath;

    @BeforeEach
    public void setUp() {
        subject = new DataProcessor();
        filePath = "./test/pedidos.json";
    }

    @Test
    public void shouldProcessData() throws Exception {
        List<Order> orders = subject.processFile(filePath);
        assertEquals(orders.size(), 2);
    }

    @Test
    public void shouldThrowFileNotFoundException() {
        filePath = "/test.fail";
        assertThrows(FileNotFoundException.class, () -> subject.processFile(filePath));
    }

    @Test
    public void shouldThrowExceptionWhenConvertingFile() {
        filePath = "./test/pedidos.csv";
        assertThrows(NumberFormatException.class, () -> subject.processFile(filePath));
    }
}
