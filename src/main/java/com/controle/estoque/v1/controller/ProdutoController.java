package com.controle.estoque.v1.controller;

import com.controle.estoque.model.Produto;
import com.controle.estoque.service.ProdutoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("produtos")
@CrossOrigin
@Data
public class ProdutoController implements WebMvcConfigurer {
    @Autowired
    ProdutoService produtoService;



    @PostMapping("/salvar")
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) throws Exception {
        try {
            return new ResponseEntity<>(produtoService.salvarProduto(produto), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /***
     * Verificar a entrada do parametros '' Authorization''
     * a api não está identificando o parametros de entrada
     *
     */
    @GetMapping("/listar-tudo")
    public ResponseEntity<List<Produto>> listarTudo() throws Exception {// /
        try {
            return new ResponseEntity<>(produtoService.listarTudo(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
         return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }



    @Transactional
    @GetMapping("listar/{id}")
    public ResponseEntity<Optional<Produto>> listarPorId(@PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(produtoService.listarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            //  e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("atualizar/{id}")
    public ResponseEntity<Produto> EditarPorId(@PathVariable Long id, @RequestBody Produto produto) throws Exception {
        try {
            return new ResponseEntity<>(produtoService.EditarPorId(id, produto), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping("deletar/{id}")
    public ResponseEntity deletarId(@PathVariable Long id) throws Exception {
        try {
            produtoService.deletarID(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            //  e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Transactional
    @GetMapping("somar-total-produtos")
    public ResponseEntity<BigDecimal> somarTotalProdutos() throws Exception {
        try {
            return new ResponseEntity<>(produtoService.somarTotalProdutos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @GetMapping("calcular-total-do-produto/{quanitidade}/{valorDaUnidade}")
    public ResponseEntity<BigDecimal> valorTotalDoProduto(@PathVariable BigDecimal quanitidade, @PathVariable BigDecimal valorDaUnidade) throws Exception {
        try {
            return new ResponseEntity<>(produtoService.valorTotalDoProduto(quanitidade, valorDaUnidade), HttpStatus.OK);
        } catch (Exception e) {
            //  e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
