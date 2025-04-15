package com.controle.estoque.v1.dto;

public record ProdutoDTO (Long id, String imagem, String descricao, Double quantidade, java.math.BigDecimal valorDeCompra, java.math.BigDecimal valorDaUnidade){}
