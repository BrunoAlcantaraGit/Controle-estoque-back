package com.controle.estoque.v1.dto;

import java.math.BigDecimal;

public record OrcamentoDTO(Long id, int quantidade, BigDecimal totalDaVenda,
                           BigDecimal lucroTransacao,BigDecimal compra, BigDecimal venda, Long cliente, Long produto) {
}
