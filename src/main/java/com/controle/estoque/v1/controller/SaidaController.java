package com.controle.estoque.v1.controller;

import com.controle.estoque.model.Cliente;
import com.controle.estoque.model.Produto;
import com.controle.estoque.model.Saida;
import com.controle.estoque.repository.ClienteRepository;
import com.controle.estoque.repository.ProdutoRepository;
import com.controle.estoque.repository.SaidaRepository;
import com.controle.estoque.service.SaidaService;
import com.controle.estoque.v1.dto.ListarSaidaDTO;
import com.controle.estoque.v1.dto.SaidaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Data
@RequestMapping("/saidas")
public class SaidaController {

    @Autowired
    SaidaService saidaService;
    @Autowired
    SaidaRepository saidaRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProdutoRepository produtoRepository;


    @PostMapping("/salvar")
    public ResponseEntity<Saida> registrarSaida(@RequestBody SaidaDTO dto)throws Exception{
        try {
          Cliente cliente = clienteRepository.findById(dto.cliente()).orElseThrow(()-> new RuntimeException("Cliente não encontrato"));
          Produto produto = produtoRepository.findById(dto.produto()).orElseThrow(()-> new RuntimeException("Produto não encontrado "));

          Saida newSaida = new Saida();

            newSaida.setTotalDaVenda(dto.totalDaVenda());
            newSaida.setProduto(produto);
            newSaida.setCliente(cliente);
            newSaida.setQuantidade(dto.quantidade());
            newSaida.setCompra(dto.compra());
            newSaida.setVenda(dto.venda());
            newSaida.setLucroTransacao(dto.lucroTransacao());

            return new ResponseEntity<>(saidaService.registrarSaida(newSaida), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("listar")
    public ResponseEntity<List<ListarSaidaDTO>>listar()throws Exception{
        try {
            return new ResponseEntity<>(saidaService.listar(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
