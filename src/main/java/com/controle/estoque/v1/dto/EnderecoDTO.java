package com.controle.estoque.v1.dto;

import com.controle.estoque.model.Endereco;

public class EnderecoDTO  {

    private Long id;
    private String logradouro;


    public String getLogradouro() {
        return logradouro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;


    }
}
