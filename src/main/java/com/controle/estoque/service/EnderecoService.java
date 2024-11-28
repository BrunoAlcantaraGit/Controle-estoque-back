package com.controle.estoque.service;

import com.controle.estoque.model.Endereco;
import com.controle.estoque.repository.EnderecoFeing;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data
public class EnderecoService {
EnderecoFeing enderecoFeing;
    public Endereco enderecoRequest(String cep){
        return enderecoFeing.enderecoAPI(cep);
    }

}
