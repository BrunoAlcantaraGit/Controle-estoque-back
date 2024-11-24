package com.controle.estoque.configuration;

import org.springframework.context.annotation.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
public class ValidarCodigo {

    public  String validarNumero(String input) {
        // Expressão regular para números inteiros e decimais (inclusive negativos)
        String regex = "^-?\\d+(\\.\\d+)?$";

        // Compila a expressão regular
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Se a string corresponder ao padrão, retorna o valor como string
        if (matcher.matches()) {
            return input;  // Retorna a string do número
        } else {
            // Caso não seja um número válido, lança uma exceção
            throw new RuntimeException("Esse campo aceita apenas números.");
        }
    }


}
