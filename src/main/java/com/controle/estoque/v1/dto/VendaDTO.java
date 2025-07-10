package com.controle.estoque.v1.dto;

import java.math.BigDecimal;
import java.util.List;

public record VendaDTO(int quantidade, Long clienteID, List<Long> produtoIds, List<Long>OrcamentoIds, double lucro, double valorTotalDaVenda) {
}
