package com.controle.estoque.service;

import com.controle.estoque.model.Endereco;
import com.controle.estoque.repository.EnderecoFeing;
import com.controle.estoque.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class EnderecoService {
EnderecoFeing enderecoFeing;
   EnderecoRepository enderecoRepository;

    public Endereco enderecoRequest(String cep){
        return enderecoFeing.enderecoAPI(cep);
    }

    @Transactional
    public Endereco atualizarEndereco(Endereco endereco,Long id) throws Exception{
        Optional<Endereco>validarEndereco = enderecoRepository.findById(id);
        if (validarEndereco.isPresent()){
            Endereco enderecoAtualizado = validarEndereco.get();
            enderecoAtualizado.setCep(endereco.getCep());
            enderecoAtualizado.setLogradouro(endereco.getLogradouro());
            enderecoAtualizado.setBairro(endereco.getBairro());
            enderecoAtualizado.setEstado(endereco.getEstado());
            enderecoAtualizado.setNumero(endereco.getNumero());

            return endereco;


        }else {
            throw new Exception("Endereco não existe");
        }
    }

}
