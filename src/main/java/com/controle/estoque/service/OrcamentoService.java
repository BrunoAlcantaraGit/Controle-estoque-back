package com.controle.estoque.service;

import com.controle.estoque.model.Orcamento;
import com.controle.estoque.repository.OrcamentoRepository;
import com.controle.estoque.v1.dto.ResponseOrcamentoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class OrcamentoService {
    @Autowired
    OrcamentoRepository orcamentoRepository;

    public Orcamento salvar(Orcamento orcamento) throws Exception {
        return orcamentoRepository.save(orcamento);
    }


    public List<ResponseOrcamentoDTO> listar() throws Exception {
        List<Orcamento> orcamentos = orcamentoRepository.findAll();
        if (!orcamentos.isEmpty()) {
            List<ResponseOrcamentoDTO> orcamentosDTO = new ArrayList<>();
            for (Orcamento s : orcamentos) {

                ResponseOrcamentoDTO ResponseOrcamentoDTO =
                        new ResponseOrcamentoDTO(
                                s.getId(),
                                s.getQuantidade(),
                                s.getTotalDaVenda(),
                                s.getLucroTransacao(),
                                s.getCliente().getNome(),
                                s.getProduto().getDescricao());

                orcamentosDTO.add(ResponseOrcamentoDTO);
            }
            return orcamentosDTO;
        } else {
            throw new Exception("não existe saídas registradas");
        }
    }


    public void deletar(Long id) throws Exception{
        Optional<Orcamento> orcamento = orcamentoRepository.findById(id);
        if (!orcamento.isEmpty()){
             orcamentoRepository.deleteById(id);
        }else{
            throw new Exception("budget not found");
        }
    }
}
