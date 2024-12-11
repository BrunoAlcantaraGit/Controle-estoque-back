package com.controle.estoque.controller;


import com.controle.estoque.model.Cliente;
import com.controle.estoque.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Data
@CrossOrigin
@RequestMapping("clientes")
public class ClienteController {
    ClienteService clienteService;


    @PostMapping("salvar")
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) throws Exception {
        try {
            return new ResponseEntity<>(clienteService.salvar(cliente), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("listar")
    public ResponseEntity<List<Cliente>> listarTudo() throws Exception {
        try {
            return new ResponseEntity<>(clienteService.listarTudo(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity deletarPoId(@PathVariable Long id) throws Exception {
        try {
            clienteService.deletarPoId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Cliente> editarPorId(@PathVariable Long id, @RequestBody Cliente cliente) throws Exception {
        try {
    return new ResponseEntity<>(clienteService.editarCliente(id,cliente),HttpStatus.OK);
        } catch (Exception e) {
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("listar/{id}")
    public ResponseEntity<Optional<Cliente>> listarPorId(@PathVariable Long id)throws Exception{
        try {
            return new ResponseEntity<>(clienteService.listarPorId(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
