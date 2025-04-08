package com.controle.estoque.v1.controller;


import com.controle.estoque.model.Fornecedor;
import com.controle.estoque.service.FornecedorService;
import com.controle.estoque.v1.dto.FornecedorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/fornecedores")
public class FornecedorController {
    @Autowired
    FornecedorService fornecedorService;

    private static final Logger logger = LoggerFactory.getLogger(FornecedorController.class);

    @PostMapping("/salvar")
    public ResponseEntity<Fornecedor> salvar(@RequestBody Fornecedor fornecedor) throws Exception {

        try {
            return new ResponseEntity<>(fornecedorService.salvar(fornecedor), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Fornecedor> ataulizarPorId(@PathVariable Long id, @RequestBody Fornecedor fornecedor) throws Exception {
        try {
            return new ResponseEntity<>(fornecedorService.ataulizarPorId(fornecedor, id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/listar/{id}")
    public ResponseEntity<Optional<Fornecedor>> listarPorId(@PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(fornecedorService.listarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Fornecedor> deletarPorId(@PathVariable Long id) throws Exception {
        try {
            fornecedorService.deletarPorId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("listartudo")
    public ResponseEntity<List<FornecedorDTO>> listarTudoDTO() throws Exception {
        try {
            return new ResponseEntity<>(fornecedorService.listarTudoDTO(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
