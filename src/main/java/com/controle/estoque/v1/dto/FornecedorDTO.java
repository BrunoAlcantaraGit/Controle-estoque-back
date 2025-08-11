package com.controle.estoque.v1.dto;

public record FornecedorDTO(
        Long id,
        String nome,
        String documento,
        String telefone,
        String email){}
