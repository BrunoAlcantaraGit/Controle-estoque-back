package com.controle.estoque.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
public class Cliente {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String documento;
   @OneToMany(cascade = CascadeType.PERSIST)
   private List<Produto> produtos;
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

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public com.controle.estoque.model.Endereco getEndereco() {
        return Endereco;
    }

    public void setEndereco(com.controle.estoque.model.Endereco endereco) {
        Endereco = endereco;
    }

   public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
