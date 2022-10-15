package br.com.alura.clientelo.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatadorDeMoeda {

    private static final Locale brasil = new Locale("pt", "BR");

    public static String CONVERTE_PARA_REAL(BigDecimal valor) {
        return NumberFormat.getCurrencyInstance(brasil).format(valor);
    }
}
