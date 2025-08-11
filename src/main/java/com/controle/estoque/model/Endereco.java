package com.controle.estoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Endereco {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)

private Long id;
private String cep;
private String numero;
private String logradouro;
private String bairro;
private String estado;
private String ibge;
private String complemento;


}
