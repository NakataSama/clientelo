package br.com.alura.clientelo.fileprocessor;

import br.com.alura.clientelo.reportgenerator.fileprocessor.FileProcessor;
import br.com.alura.clientelo.reportgenerator.report.ReportOrderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FileProcessorTest {

    private FileProcessor subject;
    private String filePath;

    @BeforeEach
    public void setUp() {
        subject = new FileProcessor();
        filePath = "pedidos.json";
    }

    @Test
    public void shouldProcessData() {
        List<ReportOrderDTO> orders = subject.processFile(filePath);
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
