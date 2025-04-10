package com.controle.estoque.v1.controller;

import com.controle.estoque.model.Produto;
import com.controle.estoque.service.ProdutoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/produtos")
@CrossOrigin
@Data
public class ProdutoController implements WebMvcConfigurer {
    @Autowired
    ProdutoService produtoService;


    @PostMapping(value = "/salvar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Produto> salvarProduto(
            @RequestParam("descricao") String descricao,
            @RequestParam("quantidade") Double quantidade,
            @RequestParam("valorDeCompra") BigDecimal valorDeCompra,
            @RequestParam("valorDaUnidade") BigDecimal valorDaUnidade,
            @RequestParam("marca") String marca,
            @RequestParam("codigo") String codigo,
            @RequestParam(value = "imagem", required = false) MultipartFile imagem

    ) throws Exception {
        Produto produto = new Produto();
        produto.setDescricao(descricao);
        produto.setQuantidade(quantidade);
        produto.setValorDeCompra(valorDeCompra);
        produto.setValorDaUnidade(valorDaUnidade);
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

    /*@PostMapping("/salvar")
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) throws Exception {
        try {
            return new ResponseEntity<>(produtoService.salvarProduto(produto), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }*/


    @GetMapping("/listar-tudo")
    public ResponseEntity<List<Produto>> listarTudo() throws Exception {// /
        try {
            return new ResponseEntity<>(produtoService.listarTudo(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }


    @Transactional
    @GetMapping("/listar/{id}")
    public ResponseEntity<Optional<Produto>> listarPorId(@PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(produtoService.listarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            //  e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Produto> EditarPorId(@PathVariable Long id, @RequestBody Produto produto) throws Exception {
        try {
            return new ResponseEntity<>(produtoService.EditarPorId(id, produto), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping("/deletar/{id}")
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
