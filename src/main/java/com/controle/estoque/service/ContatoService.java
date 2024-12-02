package com.controle.estoque.service;

import com.controle.estoque.model.Contato;
import com.controle.estoque.repository.ContatoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class ContatoService {
    @Autowired
    ContatoRepository contatoRepository;
   @Transactional
    public Contato atualizarcontato(Contato contato,Long id)throws Exception{
        Optional<Contato>validarContato = contatoRepository.findById(id);
        if (validarContato.isPresent()){
          Contato  contatoAtualizado = validarContato.get();
          contatoAtualizado.setDdd(contato.getDdd());
          contatoAtualizado.setTelefone(contato.getTelefone());
          contatoAtualizado.setEmail(contato.getEmail());
          return contato;
        }else {
            throw new Exception("contato inexistente no banco");
        }

    }
}
