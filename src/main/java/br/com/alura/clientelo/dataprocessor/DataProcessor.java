package br.com.alura.clientelo.dataprocessor;

import br.com.alura.clientelo.order.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.opencsv.CSVReader;

import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

    public static List<Order> processCsv(URL file) {
        try {
            Reader reader = Files.newBufferedReader(Path.of(file.toURI()));
            CSVReader csvReader = new CSVReader(reader);

            return OrderParser.parse(csvReader.readAll());

        } catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Order> processJson(URL file) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            return List.of(objectMapper.readValue(file, Order[].class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
