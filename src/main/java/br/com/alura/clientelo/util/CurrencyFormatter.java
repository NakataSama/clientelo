package br.com.alura.clientelo.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {

    private static final Locale brazil = new Locale("pt", "BR");

    public static String TO_BRAZIL_REAL(BigDecimal valor) {
        return NumberFormat.getCurrencyInstance(brazil).format(valor);
    }
}
