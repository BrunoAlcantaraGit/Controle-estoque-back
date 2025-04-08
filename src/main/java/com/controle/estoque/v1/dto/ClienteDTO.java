package com.controle.estoque.v1.dto;

import com.controle.estoque.model.Endereco;

public record ClienteDTO(Long id, String nome,String documento, String telefone, String email){}
