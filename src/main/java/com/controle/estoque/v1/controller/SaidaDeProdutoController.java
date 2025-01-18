package com.controle.estoque.v1.controller;

import com.controle.estoque.repository.SaidaDeProdutoRepository;
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
@Data
@AllArgsConstructor
@CrossOrigin
@RequestMapping("saida-produtos")
public class SaidaDeProdutoController {
    SaidaDeProdutoService saidaService;
    SaidaDeProdutoRepository saidaRepository;
    ProdutoService produtoService;
    ClienteService clienteService;


    @PostMapping("salvar")
    public ResponseEntity<SaidaDeProduto> salvarVenda(@RequestBody SaidaDeProdutoDTO saidaDeProdutoDTO) throws Exception {
        try {
            Optional<Produto> produto = produtoService.listarPorId(saidaDeProdutoDTO.getProduto());
            Optional<Cliente> cliente = clienteService.listarPorId(saidaDeProdutoDTO.getCliente());

            SaidaDeProduto saidaDeProduto = new SaidaDeProduto();

            saidaDeProduto.setProduto(produto.orElse(null));
            saidaDeProduto.setCliente(cliente.orElse(null));
            saidaDeProduto.setQuantidade(saidaDeProdutoDTO.getQuantidade());
            saidaDeProduto.setValorDaUnidade(saidaDeProdutoDTO.getValorDaUnidade());
            saidaDeProduto.setValorTotaldaVenda(saidaDeProdutoDTO.getValorTotaldaVenda());
            saidaDeProduto.setLucroDaTransacao(saidaDeProdutoDTO.getLucroDaTransacao());
            saidaDeProduto.setDataCadastro(saidaDeProdutoDTO.getDataCadastro());
            saidaDeProduto.setValorDeCompra(produto.get().getValorDaUnidade());

            return new ResponseEntity<>(saidaService.salvarVenda(saidaDeProduto), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }

    }

    @PutMapping("atualizarSaida/{id}")
    public ResponseEntity<SaidaDeProduto> atualizarSaida(@PathVariable Long id, @RequestBody SaidaDeProdutoDTO saidaDTO) throws Exception {
        try {
            Optional<SaidaDeProduto> saidaDeProduto = saidaService.listarSaidasPorId(id);
            Optional<Cliente> cliente = clienteService.listarPorId(saidaDTO.getCliente());
            Optional<Produto>produdo = produtoService.listarPorId(saidaDTO.getProduto());

            SaidaDeProduto saidaAtualizada = saidaDeProduto.get();
            saidaAtualizada.setCliente(cliente.orElse(null));
            saidaAtualizada.setProduto(produdo.orElse(null));
            saidaAtualizada.setValorDaUnidade(saidaDTO.getValorDaUnidade());
            saidaAtualizada.setQuantidade(saidaDTO.getQuantidade());
            saidaAtualizada.setLucroDaTransacao(saidaDTO.getLucroDaTransacao());
            saidaAtualizada.setValorTotaldaVenda(saidaDTO.getValorTotaldaVenda());
            //saidaAtualizada.setValorDeCompra(saidaDTO.getValorDeCompra());

            return new ResponseEntity<>(saidaService.atualizarSaida(id, saidaAtualizada), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("listar-tudo")
    public ResponseEntity<List<SaidaDeProduto>> listarTodasAsSaidas() throws Exception {
        try {
            return new ResponseEntity<>(saidaService.listarTodasAsSaidas(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("listar-por-id/{id}")
    public ResponseEntity<Optional<SaidaDeProduto>> listarSaidasPorId(@PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(saidaService.listarSaidasPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("valor-total-venda")
    public ResponseEntity<BigDecimal> somarTotalVenda() throws Exception {
        try {
            return new ResponseEntity<>(saidaService.somarTotalVenda(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @GetMapping("lucro-total")
    public ResponseEntity<BigDecimal> lucroTotal() throws Exception {
        try {
            return new ResponseEntity<>(saidaService.lucroTotal(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity deletarSaidaPorId(@PathVariable Long id) throws Exception {
        try {
            saidaService.deletarSaidaPorId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}