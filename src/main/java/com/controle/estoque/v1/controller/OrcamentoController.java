package com.controle.estoque.v1.controller;

import com.controle.estoque.model.Cliente;
import com.controle.estoque.model.Produto;
import com.controle.estoque.model.Orcamento;
import com.controle.estoque.repository.ClienteRepository;
import com.controle.estoque.repository.ProdutoRepository;
import com.controle.estoque.repository.OrcamentoRepository;
import com.controle.estoque.service.OrcamentoService;
import com.controle.estoque.v1.dto.OrcamentoDTO;
import com.controle.estoque.v1.dto.ResponseOrcamentoDTO;
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
@RequestMapping("/orcamento")
public class OrcamentoController {



    @Autowired
    OrcamentoService orcamentoService;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProdutoRepository produtoRepository;


    @PostMapping("/salvar")
    public ResponseEntity<Orcamento> registrarSaida(@RequestBody OrcamentoDTO dto)throws Exception{
        try {
          Cliente cliente = clienteRepository.findById(Long.valueOf(dto.cliente())).orElseThrow(()-> new RuntimeException("Cliente não encontrato"));
          Produto produto = produtoRepository.findById(Long.valueOf(dto.produto())).orElseThrow(()-> new RuntimeException("Produto não encontrado "));

          Orcamento newOrcamento = new Orcamento();

            newOrcamento.setTotalDaVenda(dto.totalDaVenda());
            newOrcamento.setProduto(produto);
            newOrcamento.setCliente(cliente);
            newOrcamento.setQuantidade(dto.quantidade());
            newOrcamento.setCompra(dto.compra());
            newOrcamento.setVenda(dto.venda());
            newOrcamento.setLucroTransacao(dto.lucroTransacao());

            return new ResponseEntity<>(orcamentoService.salvar(newOrcamento), HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("listar")
    public ResponseEntity<List<ResponseOrcamentoDTO>>listar()throws Exception{
        try {
            return new ResponseEntity<>(orcamentoService.listar(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
