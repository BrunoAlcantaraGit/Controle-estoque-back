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
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private ResponseEntity<Venda> salvar(@RequestBody VendaDTO body) throws Exception {
        try {
            Cliente cliente = clienteRepository.findById(body.clienteID()).orElseThrow(() -> new RuntimeException("Cliente não existe"));
            List<Produto> produtos = produtoRepository.findAllById(body.produtoIds());
            List<Orcamento> orcamentos = orcamentoRepository.findAllById(body.OrcamentoIds());
            Venda venda = new Venda();
            if (cliente != null && !orcamentos.isEmpty() && !produtos.isEmpty()) {

                venda.setCliente(cliente);
                venda.setProdutos(produtos);
                venda.setOrcamentos(orcamentos);
                venda.setLucro(body.lucro());
                venda.setQuantidade(body.quantidade());
                venda.setValorTotalDaVenda(body.valorTotalDaVenda());
            }
            return new ResponseEntity<>(vendaService.salvar(venda), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }
    }
}
