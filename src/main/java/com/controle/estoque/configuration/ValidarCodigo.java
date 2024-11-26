package com.controle.estoque.configuration;

import org.springframework.context.annotation.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
public class ValidarCodigo {

    public  String validarNumero(String input) {

        String regex = "^-?\\d+(\\.\\d+)?$";


        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return input;
        } else {

            throw new RuntimeException("Esse campo aceita apenas números.");
        }
    }


}
