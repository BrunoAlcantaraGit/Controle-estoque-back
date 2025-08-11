package com.controle.estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Produto {
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descricao;
    private Double quantidade;
    private BigDecimal venda;
    private BigDecimal compra;
    private String marca;
    private String codigo;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String data;
    @OneToMany
    private List<Orcamento> orcamentos;
    @ManyToMany
    private  List<Venda> vendas;

    @Lob
    @Column(length = 100000)
    private String imagem;

}

