package com.controle.estoque.v1.dto;

import java.math.BigDecimal;

public record ListarSaidaDTO(Long id, int quantidade, BigDecimal totalDaVenda, String cliente, String produto) {
}
