package com.controle.estoque.v1.controller;

import com.controle.estoque.v1.dto.SaidaDeProdutoDTO;
import com.controle.estoque.model.Cliente;
import com.controle.estoque.model.Produto;
import com.controle.estoque.model.SaidaDeProduto;
import com.controle.estoque.service.ClienteService;
import com.controle.estoque.service.ProdutoService;
import com.controle.estoque.service.SaidaDeProdutoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@Data
@AllArgsConstructor
@RequestMapping("saida-produtos")
public class SaidaDeProdutoController {
    SaidaDeProdutoService saidaService;
    ProdutoService produtoService;
    ClienteService clienteService;

    @PostMapping("salvar")
    public ResponseEntity<SaidaDeProduto> salvarVenda(@RequestBody SaidaDeProdutoDTO saidaDeProdutoDTO) throws Exception {
        try {
            Optional<Produto> produto = produtoService.listarPorId(saidaDeProdutoDTO.getProduto());
            Optional<Cliente> cliente = clienteService.listarPorId(saidaDeProdutoDTO.getCliente());

            SaidaDeProduto saidaDeProduto = new SaidaDeProduto();
            saidaDeProduto.setProduto( produto.orElse(null));
            saidaDeProduto.setCliente( cliente.orElse(null));
            saidaDeProduto.setQuantidade(saidaDeProdutoDTO.getQuantidade());
            saidaDeProduto.setValorDaUnidade(saidaDeProdutoDTO.getValorDaUnidade());
            saidaDeProduto.setValorTotaldaVenda(saidaDeProdutoDTO.getValorTotaldaVenda());
            saidaDeProduto.setDataCadastro(saidaDeProdutoDTO.getDataCadastro());


            return new ResponseEntity<>(saidaService.salvarVenda(saidaDeProduto), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }

    }
@GetMapping("listar-tudo")
public ResponseEntity<List<SaidaDeProduto>>listarTodasAsSaidas() throws Exception {
      try {
        return   new ResponseEntity<>(saidaService.listarTodasAsSaidas(),HttpStatus.OK);
      }catch (Exception e){
         return   new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    }

@GetMapping("valor-total-venda")
public  ResponseEntity<BigDecimal>somarTotalVenda()throws Exception{
        try {
           return new ResponseEntity<>(saidaService.somarTotalVenda(),HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
}




}