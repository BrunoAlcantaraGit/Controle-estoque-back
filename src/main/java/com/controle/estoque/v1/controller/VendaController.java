package com.controle.estoque.v1.controller;

import com.controle.estoque.model.Cliente;
import com.controle.estoque.model.Orcamento;
import com.controle.estoque.model.Produto;
import com.controle.estoque.model.Venda;
import com.controle.estoque.repository.ClienteRepository;
import com.controle.estoque.repository.OrcamentoRepository;
import com.controle.estoque.repository.ProdutoRepository;
import com.controle.estoque.repository.VendaRepository;
import com.controle.estoque.service.VendaService;
import com.controle.estoque.v1.dto.VendaDTO;
import com.controle.estoque.v1.dto.VendaResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("venda")
public class VendaController {
    @Autowired
    private VendaService vendaService;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @PostMapping("/salvar")
    public ResponseEntity<Venda> salvar(@RequestBody VendaDTO body) throws Exception {
        try {
            Cliente cliente = clienteRepository.findById(body.clienteId()).orElseThrow(() -> new RuntimeException("Cliente não existe"));
            List<Produto> produtos = produtoRepository.findAllById(body.produtoIds());
            List<Orcamento> orcamentos = orcamentoRepository.findAllById(body.orcamentoIds());
            Venda venda = new Venda();
            if (cliente != null && !orcamentos.isEmpty() && !produtos.isEmpty()) {

                venda.setCliente(cliente);
                venda.setProdutos(produtos);
                venda.setOrcamentos(orcamentos);
                venda.setLucro(body.lucro());
                venda.setValorTotalDaVenda(body.valorTotalDaVenda());
            }
            return new ResponseEntity<>(vendaService.salvar(venda), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }
    }
@Transactional
@GetMapping("/listar")
    public ResponseEntity<List<VendaResponseDTO>>listar()throws Exception{
        try {
            return new ResponseEntity<>(vendaService.listar(),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
}
}
