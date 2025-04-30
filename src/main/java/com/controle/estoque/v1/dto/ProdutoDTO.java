package com.controle.estoque.v1.dto;

public record ProdutoDTO (Long id, String imagem, String descricao, Double quantidade, java.math.BigDecimal venda, java.math.BigDecimal compra){}
