package com.controle.estoque.service;

import com.controle.estoque.model.Endereco;
import com.controle.estoque.repository.EnderecoFeing;
import com.controle.estoque.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class EnderecoService {
    EnderecoFeing enderecoFeing;
    EnderecoRepository enderecoRepository;

    public Endereco enderecoRequest(String cep) {
        return enderecoFeing.enderecoAPI(cep);
    }


    public void deletarPorId(Long id) throws Exception {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isPresent()) {
            enderecoRepository.deleteById(id);
        } else {
            throw new Exception("Id não localizado");
        }
    }

public List<Endereco>  listarEnderecos()throws Exception{
     List<Endereco>  enderecos = enderecoRepository.findAll();
     if (!enderecos.isEmpty()){
         List<Endereco>exibirEndereco = new ArrayList<>();
         for (Endereco e:enderecos){
             exibirEndereco.add(e);
         }
     return exibirEndereco;
     }else {
         throw new Exception("Lista não contem elementos");
     }
}

}
