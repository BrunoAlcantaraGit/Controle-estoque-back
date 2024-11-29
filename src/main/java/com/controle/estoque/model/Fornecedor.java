package com.controle.estoque.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpf;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Contato> contatos;
    @OneToOne(cascade = CascadeType.ALL)
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public com.controle.estoque.model.Endereco getEndereco() {
        return Endereco;
    }

    public void setEndereco(com.controle.estoque.model.Endereco endereco) {
        Endereco = endereco;
    }
}
