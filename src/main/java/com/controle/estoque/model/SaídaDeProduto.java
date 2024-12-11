package com.controle.estoque.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class SaídaDeProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantidade;
    private BigDecimal valordaVendaUnidade;
    private BigDecimal valorTotaldaVenda;
    @OneToOne(cascade = CascadeType.ALL)
    private Produto produto;
    @OneToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValordaVendaUnidade() {
        return valordaVendaUnidade;
    }

    public void setValordaVendaUnidade(BigDecimal valordaVendaUnidade) {
        this.valordaVendaUnidade = valordaVendaUnidade;
    }

    public BigDecimal getValorTotaldaVenda() {
        return valorTotaldaVenda;
    }

    public void setValorTotaldaVenda(BigDecimal valorTotaldaVenda) {
        this.valorTotaldaVenda = valorTotaldaVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
