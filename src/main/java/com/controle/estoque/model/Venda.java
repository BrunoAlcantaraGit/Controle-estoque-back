package com.controle.estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private double lucro;
    private double valorTotalDaVenda;
    @ManyToMany
    List<Produto> produtos;
    @ManyToMany
    List<Orcamento> orcamentos;
    @ManyToOne
    Cliente cliente;



}
