package com.controle.estoque.v1.controller;

import com.controle.estoque.model.Produto;
import com.controle.estoque.service.ProdutoService;
import com.controle.estoque.v1.dto.ProdutoDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/produtos")
@CrossOrigin
@Data
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;


    @PostMapping(value = "/salvar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Produto> salvarProduto(
            @RequestParam("descricao") String descricao,
            @RequestParam("quantidade") Double quantidade,
            @RequestParam("venda") BigDecimal venda,
            @RequestParam("compra") BigDecimal compra,
            @RequestParam("marca") String marca,
            @RequestParam("codigo") String codigo,
            @RequestParam(value = "imagem", required = false) MultipartFile imagem

    ) throws Exception {
        Produto produto = new Produto();
        produto.setDescricao(descricao);
        produto.setQuantidade(quantidade);
        produto.setCompra(compra);
        produto.setVenda(venda);
        produto.setMarca(marca);
        produto.setCodigo(codigo);

        if (imagem != null && !imagem.isEmpty()) {
            try {
                byte[] bytes = imagem.getBytes();
                String base64 = Base64.getEncoder().encodeToString(bytes);
                produto.setImagem(base64);
            } catch (IOException e) {
            }
        }
        return new ResponseEntity<>(produtoService.salvarProduto(produto), HttpStatus.OK);
    }


    @PutMapping(value = "/atualizar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Produto> editarPorId(@PathVariable Long id,
                                               @RequestParam("descricao") String descricao,
                                               @RequestParam("quantidade") Double quantidade,
                                               @RequestParam("venda") BigDecimal venda,
                                               @RequestParam("compra") BigDecimal compra,
                                               @RequestParam("marca") String marca,
                                               @RequestParam("codigo") String codigo,
                                               @RequestParam(value = "imagem", required = false) MultipartFile imagem
    ) throws Exception {

        Optional<Produto> produto = produtoService.listarPorId(id);

        Produto produtoAtualizado = produto.get();

        produtoAtualizado.setQuantidade(quantidade);
        produtoAtualizado.setVenda(venda);
        produtoAtualizado.setCompra(compra);
        produtoAtualizado.setMarca(marca);
        produtoAtualizado.setDescricao(descricao);
        produtoAtualizado.setCodigo(codigo);

        if (imagem != null && !imagem.isEmpty()) {
            try {
                byte[] bytes = imagem.getBytes();
                String base64 = Base64.getEncoder().encodeToString(bytes);
                produtoAtualizado.setImagem(base64);
            } catch (IOException e) {
            }
        }
        return new ResponseEntity<>(produtoService.editarPorId(id,produtoAtualizado), HttpStatus.OK);
    }


    @GetMapping("/listar-tudo")
    public ResponseEntity<List<ProdutoDTO>> listarTudo() throws Exception {// /
        try {
            return new ResponseEntity<>(produtoService.listarTudo(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }


    @Transactional
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Produto>> listarPorId(@PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(produtoService.listarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Transactional
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarId(@PathVariable Long id) throws Exception {
        try {
            if(id==null){
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            produtoService.deletarID(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            //  e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @Transactional
    @GetMapping("/somar-total-produtos")
    public ResponseEntity<BigDecimal> somarTotalProdutos() throws Exception {
        try {
            return new ResponseEntity<>(produtoService.somarTotalProdutos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @GetMapping("/calcular-total-do-produto/{quanitidade}/{valorDaUnidade}")
    public ResponseEntity<BigDecimal> valorTotalDoProduto(@PathVariable BigDecimal quanitidade, @PathVariable BigDecimal valorDaUnidade) throws Exception {
        try {
            return new ResponseEntity<>(produtoService.valorTotalDoProduto(quanitidade, valorDaUnidade), HttpStatus.OK);
        } catch (Exception e) {
            //  e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
