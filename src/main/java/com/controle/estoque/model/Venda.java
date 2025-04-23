package com.controle.estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codigo;
    private Double UnidadeDaVenda;
    private Double totalDaVenda;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String data;
    @ManyToMany
    private List<Produto> produtos;
    @ManyToOne
    private Cliente cliente;


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getUnidadeDaVenda() {
        return UnidadeDaVenda;
    }

    public void setUnidadeDaVenda(Double unidadeDaVenda) {
        UnidadeDaVenda = unidadeDaVenda;
    }

    public Double getTotalDaVenda() {
        return totalDaVenda;
    }

    public void setTotalDaVenda(Double totalDaVenda) {
        this.totalDaVenda = totalDaVenda;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
