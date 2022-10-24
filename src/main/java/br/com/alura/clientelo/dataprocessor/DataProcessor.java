package br.com.alura.clientelo.dataprocessor;

import br.com.alura.clientelo.order.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.opencsv.CSVReader;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

    public static List<Order> processCsv(String file) {

        List<Order> result = new ArrayList<>();

        try {
            Reader reader = Files.newBufferedReader(Path.of(ClassLoader.getSystemResource(file).toURI()));
            CSVReader csvReader = new CSVReader(reader);

            result = OrderParser.parse(csvReader.readAll());
            return result;

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static List<Order> processJson(String file) {

        List<Order> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            result = List.of(objectMapper.readValue(ClassLoader.getSystemResource(file), Order[].class));
            return result;
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
