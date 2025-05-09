package com.controle.estoque.v1.dto;

import java.math.BigDecimal;

public record SaidaDTO(Long id, int quantidade, BigDecimal lucroTransacao,
                       Long cliente, Long produto, BigDecimal compra, BigDecimal venda, BigDecimal totalDaVenda) {
}
