package com.controle.estoque.service;

import com.controle.estoque.model.SaídaDeProduto;
import com.controle.estoque.repository.SaindaDeProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class SaidaDeProdutoService {

     SaindaDeProdutoRepository saidaRepository;

    public SaídaDeProduto salvarVenda(SaídaDeProduto saida) throws Exception{
       Optional<SaídaDeProduto> validarVenda= saidaRepository.findById(saida.getId());
     if (validarVenda.isEmpty()){
     return  saidaRepository.save(saida);

     }else {
         throw new Exception("ID não informado");
     }

    }


}
