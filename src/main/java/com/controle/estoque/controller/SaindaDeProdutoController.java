package com.controle.estoque.controller;

import com.controle.estoque.model.SaídaDeProduto;
import com.controle.estoque.service.SaidaDeProdutoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Data
@AllArgsConstructor
@RequestMapping("saida-produto")
public class SaindaDeProdutoController {
    SaidaDeProdutoService saidaService;
    @PostMapping("salvar")
    public ResponseEntity<SaídaDeProduto>salvarVenda(@RequestBody SaídaDeProduto saida)throws Exception {
    try {
        return new ResponseEntity<>(saidaService.salvarVenda(saida), HttpStatus.OK);
    }catch (Exception e){
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    }

}
