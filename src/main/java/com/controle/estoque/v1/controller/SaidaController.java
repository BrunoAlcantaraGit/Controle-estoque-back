package com.controle.estoque.v1.controller;

import com.controle.estoque.model.Saida;
import com.controle.estoque.service.SaidaService;
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
@RequestMapping("/saidas")
public class SaidaController {

    @Autowired
    SaidaService saidaService;

    @PostMapping("/salvar")
    public ResponseEntity<Saida> registrarSaida(@RequestBody Saida saida)throws Exception{
        try {
            return new ResponseEntity<>(saidaService.registrarSaida(saida), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
