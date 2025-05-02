package com.controle.estoque.v1.dto;

import com.controle.estoque.model.Cliente;

import java.math.BigDecimal;

public record SaidaDTO(
        int quantidade,BigDecimal venda, BigDecimal compra,BigDecimal
         totalDaVenda, BigDecimal lucroTransacao,Long cliente, Long produto){}
