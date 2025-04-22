package com.controle.estoque.v1.controller;

import com.controle.estoque.model.Venda;
import com.controle.estoque.service.VendaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Data
@RequestMapping("/vendas")
public class VendasController {
    @Autowired
    VendaService vendaService;

    @PostMapping("/salvar")
    public ResponseEntity<Venda> salvar(@RequestBody Venda venda) throws Exception{
        try {
            return new ResponseEntity<>(vendaService.salvar(venda), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
