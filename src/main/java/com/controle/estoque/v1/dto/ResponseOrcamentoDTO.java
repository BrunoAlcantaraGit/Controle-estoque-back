package com.controle.estoque.v1.dto;

import java.math.BigDecimal;

public record ResponseOrcamentoDTO(Long id, int quantidade, BigDecimal totalDaVenda, BigDecimal lucroTransacao, String cliente, String produto, Long clienteID, Long produtoID) {
}
