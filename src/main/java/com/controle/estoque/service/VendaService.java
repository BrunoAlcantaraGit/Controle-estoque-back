package com.controle.estoque.service;

import com.controle.estoque.model.Venda;
import com.controle.estoque.repository.VendasRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
@Data
public class VendaService {

    @Autowired
    VendasRepository vendasRepository;

    public Venda salvar(Venda venda) throws Exception{
        return vendasRepository.save(venda);
    }
}
