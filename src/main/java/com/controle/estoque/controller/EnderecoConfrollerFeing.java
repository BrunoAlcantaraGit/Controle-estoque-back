package com.controle.estoque.controller;

import com.controle.estoque.model.Endereco;
import com.controle.estoque.service.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/endereco")
public class EnderecoConfrollerFeing {

    EnderecoService enderecoService;
    @GetMapping("endereco-api/{cep}")
    public ResponseEntity<Endereco> retornoEndereco(@PathVariable String cep){
        return new ResponseEntity<>(enderecoService.enderecoRequest(cep), HttpStatus.CREATED);
    }
}
