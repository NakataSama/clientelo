package br.com.alura.clientelo.result;

import br.com.alura.clientelo.reportgenerator.fileprocessor.FileProcessor;
import br.com.alura.clientelo.reportgenerator.report.ReportOrderDTO;
import br.com.alura.clientelo.mock.ResultMocks;
import br.com.alura.clientelo.reportgenerator.report.impl.GeneralReport;
import br.com.alura.clientelo.reportgenerator.report.result.impl.GeneralReportResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeneralReportResultTest {

    private GeneralReportResult subject;
    private GeneralReport report;
    private List<ReportOrderDTO> orders;
    private String filePath;

    @BeforeEach
    public void setUp() throws Exception {
        report = new GeneralReport();
        filePath = "pedidos.json";
        orders = new FileProcessor().processFile(filePath);
    }

    @Test
    public void shouldGenerateText() {
        subject = (GeneralReportResult) report.process(orders, null);
        assertEquals(ResultMocks.getGeneralReportMock(), subject.generateText());
    }

    @Test
    public void shouldThrowExceptionWhenResultIsInvalid() {
        assertThrows(RuntimeException.class, () -> subject.generateText());
    }
}
