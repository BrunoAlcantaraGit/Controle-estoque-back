package com.controle.estoque.v1.dto;

public class FornecedorDTO {

    private Long id;
    private String nome;
    private String documento;
    private EnderecoDTO endereco;

    public FornecedorDTO(Long id, String nome, String documento, EnderecoDTO endereco) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
