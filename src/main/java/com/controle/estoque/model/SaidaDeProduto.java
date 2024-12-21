package com.controle.estoque.model;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class SaidaDeProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantidade;
    private BigDecimal valorDaUnidade;
    private BigDecimal valorTotaldaVenda;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Cliente cliente;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String dataCadastro;

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

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}