package com.controle.estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getEntityVenda() {
        return entityVenda;
    }

    public void setEntityVenda(Venda entityVenda) {
        this.entityVenda = entityVenda;
    }

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

    public BigDecimal getCompra() {
        return compra;
    }

    public void setCompra(BigDecimal compra) {
        this.compra = compra;
    }

    public BigDecimal getVenda() {
        return venda;
    }

    public void setVenda(BigDecimal venda) {
        this.venda = venda;
    }

    public BigDecimal getTotalDaVenda() {
        return totalDaVenda;
    }

    public void setTotalDaVenda(BigDecimal totalDaVenda) {
        this.totalDaVenda = totalDaVenda;
    }

    public BigDecimal getLucroTransacao() {
        return lucroTransacao;
    }

    public void setLucroTransacao(BigDecimal lucroTransacao) {
        this.lucroTransacao = lucroTransacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
