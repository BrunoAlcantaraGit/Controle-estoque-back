package com.controle.estoque.v1.dto;

import java.math.BigDecimal;
import java.util.List;

public record VendaDTO(List<Long>saida, BigDecimal total, BigDecimal lucro, int quantidade) {
}
