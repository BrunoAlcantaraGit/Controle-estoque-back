package com.controle.estoque.controller;

import com.controle.estoque.model.Contato;
import com.controle.estoque.service.ContatoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin
@Data
@RequestMapping("contato")
public class ContatoController {

    ContatoService contatoService;
    @PutMapping("atualizar-contato/{id}")
    public ResponseEntity<Contato> atualizarcontato(@RequestBody Contato contato, @PathVariable Long id) throws Exception{
        try {
            return new ResponseEntity<>(contatoService.atualizarcontato(contato,id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
