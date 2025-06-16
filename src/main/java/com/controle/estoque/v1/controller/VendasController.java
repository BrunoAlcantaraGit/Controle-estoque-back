package com.controle.estoque.v1.controller;

import com.controle.estoque.model.Orcamento;
import com.controle.estoque.model.Venda;
import com.controle.estoque.repository.OrcamentoRepository;
import com.controle.estoque.service.VendaService;
import com.controle.estoque.v1.dto.VendaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@Data
@RequestMapping("/vendas")
public class VendasController {
    @Autowired
    VendaService vendaService;
    @Autowired
    OrcamentoRepository orcamentoRepository;

    @PostMapping("/salvar")
    public ResponseEntity<Venda> salvar(@RequestBody VendaDTO dto) throws Exception{
        try {
        List<Orcamento> orcamentos = orcamentoRepository.findAllById(dto.saida());
            BigDecimal lucro = orcamentos.stream().
                    map(saida ->saida.getLucroTransacao())
                    .filter(lucroTransacao -> lucroTransacao != null)
                    .reduce(BigDecimal.ZERO,BigDecimal::add);

            BigDecimal totalvenda = orcamentos.stream()
                    .map(saida -> saida.getVenda())
                    .filter(venda-> venda != null)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            Venda venda = new Venda();

         venda.setSaidas(orcamentos);
         venda.setLucro(lucro);
         venda.setTotal(totalvenda);
         venda.setQuantidade(orcamentos.size());

          return new ResponseEntity<>(vendaService.salvar(venda), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
