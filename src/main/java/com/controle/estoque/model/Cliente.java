package com.controle.estoque.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@Entity
public class Cliente {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String documento;

    @OneToMany
    private List<Venda> vendas;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Contato contato;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Endereco Endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Endereco getEndereco() {
        return Endereco;
    }

    public void setEndereco(Endereco endereco) {
        Endereco = endereco;
    }
}
