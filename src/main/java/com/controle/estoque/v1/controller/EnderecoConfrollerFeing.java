package com.controle.estoque.v1.controller;

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

    @PutMapping("atualizar-endereco/{id}")
    public ResponseEntity<Endereco>atualizarEndereco(@RequestBody Endereco endereco,@PathVariable Long id) throws Exception{
        try {
            return new ResponseEntity<>(enderecoService.atualizarEndereco(endereco,id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

@DeleteMapping("deletar/{id}")
    public ResponseEntity deletarPorId(@PathVariable Long id)throws Exception{
        try {
            enderecoService.deletarPorId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
}
}
