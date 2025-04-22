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



}
