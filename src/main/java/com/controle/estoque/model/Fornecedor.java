package com.controle.estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


@Entity
@ToString
@Data
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


}
