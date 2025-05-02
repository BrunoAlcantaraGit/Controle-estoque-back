package com.controle.estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
public class Saida {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private int quantidade;
    private BigDecimal compra;
    private BigDecimal venda;
    private BigDecimal totalVendido;
    private BigDecimal LucroDaTransacao;
    @ManyToOne
    private Cliente cliente;
   @ManyToOne
    private Produto  produto;

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

    public BigDecimal getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(BigDecimal totalVendido) {
        this.totalVendido = totalVendido;
    }

    public BigDecimal getLucroDaTransacao() {
        return LucroDaTransacao;
    }

    public void setLucroDaTransacao(BigDecimal lucroDaTransacao) {
        LucroDaTransacao = lucroDaTransacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
