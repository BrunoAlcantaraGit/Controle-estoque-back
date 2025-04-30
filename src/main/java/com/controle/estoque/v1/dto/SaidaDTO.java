package com.controle.estoque.v1.dto;

import com.controle.estoque.model.Cliente;

import java.math.BigDecimal;

public record SaidaDTO(int quantidade, BigDecimal lucroDaTransacao, String nome, String email) {
}
