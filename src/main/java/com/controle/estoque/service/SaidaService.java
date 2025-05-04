package com.controle.estoque.service;

import com.controle.estoque.model.Saida;
import com.controle.estoque.repository.SaidaRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data
public class SaidaService {
    @Autowired
    SaidaRepository saidaRepository;

    public Saida registrarSaida(Saida saida)throws Exception{

        return saidaRepository.save(saida);
    }
}
