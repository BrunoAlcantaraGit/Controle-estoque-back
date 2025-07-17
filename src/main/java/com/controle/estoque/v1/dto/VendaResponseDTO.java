package com.controle.estoque.v1.dto;

import com.controle.estoque.model.Orcamento;
import com.controle.estoque.model.Produto;

import java.util.List;

public record VendaResponseDTO(com.controle.estoque.model.Cliente cliente, List<Produto> produtoIds, List<Orcamento> orcamentoIds, double lucro, double valorTotalDaVenda) {
}
