package br.com.alura.clientelo.dataprocessor;

import br.com.alura.clientelo.order.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DataProcessor {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final Pattern json = Pattern.compile("^.*\\.(json)$");
    private final Pattern csv = Pattern.compile("^.*\\.(csv)$");

    public List<Order> processFile(String file) {
        try {
            List<Order> response = new ArrayList<>();
            boolean isJson = json.matcher(file).find();
            boolean isCsv = csv.matcher(file).find();

            URL fileURL = validateFile(file, isJson, isCsv);
            if (isJson)
                response = processJson(fileURL);
            if (isCsv)
                response = processCsv(fileURL);

            return response;
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing file: %s", e));
        }
    }

    private URL validateFile(String file, boolean isJson, boolean isCsv) throws FileNotFoundException {

        URL fileUrl = ClassLoader.getSystemResource(file);
        if((isJson || isCsv) && fileUrl != null)
            return fileUrl;

        throw new FileNotFoundException();
    }

    private List<Order> processCsv(URL file) throws IOException, URISyntaxException {
        Reader reader = Files.newBufferedReader(Path.of(file.toURI()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> orders = csvReader.readAll();

        return OrderParser.parse(orders);
    }

    private List<Order> processJson(URL file) throws IOException {
        objectMapper.registerModule(new JavaTimeModule());
        Order[] orders = objectMapper.readValue(file, Order[].class);

        return List.of(orders);
    }
}
