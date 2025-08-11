package com.controle.estoque.configuration;

import lombok.ToString;
import org.springframework.context.annotation.Configuration;

@ToString
@Configuration
public class DocumentFormatter {

    public String formatarDocumento(String doc) throws Exception {

        if (doc == null) {
            return null;
        }

        String digits = doc.replaceAll("\\D", "");

        if (digits.length() == 11) { // CPF
            if (isCpfValido(digits)) {
                return formatCpf(digits);
            } else {
                throw new IllegalArgumentException("CPF inválido");
            }
        } else if (digits.length() == 14) { // CNPJ
            if (isCnpjValido(digits)) {
                return formatCnpj(digits);
            } else {
             throw new IllegalArgumentException("CNPJ inválido");
            }
        }

        throw new RuntimeException("Numero do documento informado não corresponde nem ao CPF nem oa CNPJ ");
    }

    public boolean isCpfValido(String cpf) {
        if (cpf == null) return false;
        String d = cpf.replaceAll("\\D", "");
        if (d.length() != 11) return false;

        if (d.matches("(\\d)\\1{10}")) return false;

        int[] num = new int[11];
        for (int i = 0; i < 11; i++) num[i] = d.charAt(i) - '0';

        // primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) soma += num[i] * (10 - i);
        int resto = soma % 11;
        int dig1 = (resto < 2) ? 0 : 11 - resto;
        if (dig1 != num[9]) return false;

        // segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) soma += num[i] * (11 - i);
        resto = soma % 11;
        int dig2 = (resto < 2) ? 0 : 11 - resto;
        return dig2 == num[10];
    }

    public boolean isCnpjValido(String cnpj) {
        if (cnpj == null) return false;
        String d = cnpj.replaceAll("\\D", "");
        if (d.length() != 14) return false;
        // rejeita sequências iguais
        if (d.matches("(\\d)\\1{13}")) return false;

        int[] num = new int[14];
        for (int i = 0; i < 14; i++) num[i] = d.charAt(i) - '0';

        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        for (int i = 0; i < 12; i++) soma += num[i] * pesos1[i];
        int resto = soma % 11;
        int dig1 = (resto < 2) ? 0 : 11 - resto;
        if (dig1 != num[12]) return false;

        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        soma = 0;
        for (int i = 0; i < 13; i++) soma += num[i] * pesos2[i];
        resto = soma % 11;
        int dig2 = (resto < 2) ? 0 : 11 - resto;
        return dig2 == num[13];
    }

    private String formatCpf(String digitsOnly) {
        return digitsOnly.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    private String formatCnpj(String digitsOnly) {
        return digitsOnly.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }
}
