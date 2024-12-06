package com.controle.estoque.model;

import jakarta.persistence.*;


@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String documento;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Contato contato;
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco Endereco;

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

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

    public void setCpf(String documento) {
        this.documento = documento;
    }


    public com.controle.estoque.model.Endereco getEndereco() {
        return Endereco;
    }

    public void setEndereco(com.controle.estoque.model.Endereco endereco) {
        Endereco = endereco;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", documento='" + documento + '\'' +
                ", Endereco=" + Endereco +
                '}';
    }
}
