package com.controle.estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

@Data
@Entity
public class Produto {
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descricao;
    private Double quantidade;
    private BigDecimal valorDaUnidade;
    private BigDecimal valorDeCompra;
    private String marca;
    private String codigo;
    @OneToOne(cascade = CascadeType.ALL)
    private Total total;
    @OneToOne(cascade = CascadeType.ALL)
    private Categoria categoria;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String dataCadastro;


    public BigDecimal getValorDaUnidade() {
        return valorDaUnidade;
    }

    public void setValorDaUnidade(BigDecimal valorDaUnidade) {
        this.valorDaUnidade = valorDaUnidade;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorDeCompra() {
        return valorDeCompra;
    }

    public void setValorDeCompra(BigDecimal valorDeCompra) {
        this.valorDeCompra = valorDeCompra;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}

