package com.controle.estoque.v1.dto;

import java.math.BigDecimal;
import java.util.List;

    public record VendaDTO( Long clienteId, List<Long> produtoIds, List<Long>orcamentoIds, double lucro, double valorTotalDaVenda) {
}
