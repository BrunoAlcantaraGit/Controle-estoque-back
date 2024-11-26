package com.controle.estoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Contato {
    @Id
    @GeneratedValue
    Long id;
    private String telefone;
    private String ddd;
    private String email;
}
