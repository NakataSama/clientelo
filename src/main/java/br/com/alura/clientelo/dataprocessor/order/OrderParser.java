package br.com.alura.clientelo.dataprocessor.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderParser {

    public static List<OrderDTO> parse(List<String[]> content) {
        return content.stream().skip(1)
                .map(order -> {
                    String category = order[0];
                    String product = order[1];
                    BigDecimal price = new BigDecimal(order[2]);
                    int quantity = Integer.parseInt(order[3]);
                    LocalDate date = LocalDate.parse(order[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    String customer = order[5];
                    return new OrderDTO(category, product, customer, price, quantity, date);
                }).toList();
    }
}
