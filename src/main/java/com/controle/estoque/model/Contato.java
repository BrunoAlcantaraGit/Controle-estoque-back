package com.controle.estoque.model;

import jakarta.persistence.*;

@Entity
public class Contato {
    @Id
    @GeneratedValue
    Long id;
    private String telefone;
    private String ddd;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    private Fornecedor fornecedor;
}
