package com.controle.estoque.model;

import jakarta.persistence.*;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String nome;
    private String cnpj;
    @OneToMany(cascade = CascadeType.ALL)
    private Contato contato;
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco Endereco;
}
