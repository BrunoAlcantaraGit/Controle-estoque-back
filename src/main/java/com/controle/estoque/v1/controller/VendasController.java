package com.controle.estoque.v1.controller;

import com.controle.estoque.model.Saida;
import com.controle.estoque.model.Venda;
import com.controle.estoque.repository.SaidaRepository;
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
    SaidaRepository saidaRepository;

    @PostMapping("/salvar")
    public ResponseEntity<Venda> salvar(@RequestBody VendaDTO dto) throws Exception{
        try {
        List<Saida> saidas= saidaRepository.findAllById(dto.saida());
            BigDecimal lucro = saidas.stream().
                    map(saida ->saida.getLucroTransacao())
                    .filter(lucroTransacao -> lucroTransacao != null)
                    .reduce(BigDecimal.ZERO,BigDecimal::add);

            BigDecimal totalvenda = saidas.stream()
                    .map(saida -> saida.getVenda())
                    .filter(venda-> venda != null)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            Venda venda = new Venda();

         venda.setSaidas(saidas);
         venda.setLucro(lucro);
         venda.setTotal(totalvenda);
         venda.setQuantidade(saidas.size());

          return new ResponseEntity<>(vendaService.salvar(venda), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
