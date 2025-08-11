package com.controle.estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Data
@Entity
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private int quantidade;
    private BigDecimal compra;
    private BigDecimal venda;
    private BigDecimal totalDaVenda;
    private BigDecimal lucroTransacao;

    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Produto  produto;
    @ManyToOne
    private Venda entityVenda;



}
