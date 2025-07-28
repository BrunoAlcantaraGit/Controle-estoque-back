package com.controle.estoque.v1.dto;
import java.util.List;
public record VendaResponseDTO(String cliente, List<Long> produtoIds, List<Long> orcamentoIds, double lucro, double valorTotalDaVenda) {
}
