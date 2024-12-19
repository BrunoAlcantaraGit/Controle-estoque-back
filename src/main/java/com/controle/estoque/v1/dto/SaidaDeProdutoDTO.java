package com.controle.estoque.v1.dto;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
public class SaidaDeProdutoDTO {


    private Long id;
    private int quantidade;
    private BigDecimal valorDaUnidade;
    private BigDecimal valorTotaldaVenda;
    private Long produto;
    private Long cliente;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String data;

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

    public BigDecimal getValorDaUnidade() {
        return valorDaUnidade;
    }

    public void setValorDaUnidade(BigDecimal valorDaUnidade) {
        this.valorDaUnidade = valorDaUnidade;
    }

    public BigDecimal getValorTotaldaVenda() {
        return valorTotaldaVenda;
    }

    public void setValorTotaldaVenda(BigDecimal valorTotaldaVenda) {
        this.valorTotaldaVenda = valorTotaldaVenda;
    }

    public Long getProduto() {
        return produto;
    }

    public void setProduto(Long produto) {
        this.produto = produto;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
