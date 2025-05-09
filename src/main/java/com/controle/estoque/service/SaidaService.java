package com.controle.estoque.service;

import com.controle.estoque.model.Saida;
import com.controle.estoque.repository.SaidaRepository;
import com.controle.estoque.v1.dto.SaidaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Data
public class SaidaService {
    @Autowired
    SaidaRepository saidaRepository;

        public Saida registrarSaida(Saida saida)throws Exception{
        return saidaRepository.save(saida);
    }

    public List<SaidaDTO> listarSaida() throws Exception{
        List<Saida> saidas = saidaRepository.findAll();
        if(!saidas.isEmpty()){
            List<SaidaDTO>saidasDTO = new ArrayList<>();
            for (Saida s:saidas){

                SaidaDTO saidaDTO = new SaidaDTO(s.getId(),s.getQuantidade(),s.getLucroTransacao(),s.getCliente().getId(),
                        s.getProduto().getId(),s.getVenda(),s.getCompra(),s.getTotalDaVenda());
            }
            return saidasDTO;
        }else {
            throw new Exception("não existe saídas registradas");
        }
    }
}
