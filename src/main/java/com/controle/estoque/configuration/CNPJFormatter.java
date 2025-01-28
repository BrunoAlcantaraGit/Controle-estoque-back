package com.controle.estoque.configuration;


import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
@Configuration
public class CNPJFormatter {

    public String formatarCNPJ(String cnpj) {

        if (cnpj == null || !cnpj.matches("\\d{14}")) {
            return null;
        }


        Pattern pattern = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");
        Matcher matcher = pattern.matcher(cnpj);

        if (matcher.matches()) {
            return matcher.replaceAll("$1.$2.$3/$4-$5");
        }

        return null;
    }
}