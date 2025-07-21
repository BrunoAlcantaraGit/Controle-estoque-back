package com.controle.estoque.v1.dto;

import com.controle.estoque.model.Orcamento;
import com.controle.estoque.model.Produto;

import java.util.List;
import java.util.stream.Stream;

public record VendaResponseDTO(String cliente, List<Long> produtoIds, List<Long> orcamentoIds, double lucro, double valorTotalDaVenda) {
}
