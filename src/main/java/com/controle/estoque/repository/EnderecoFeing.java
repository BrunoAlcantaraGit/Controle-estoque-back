package com.controle.estoque.repository;

import com.controle.estoque.model.Endereco;
import feign.FeignIgnore;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "viacep.com.br/ws", name = "viacep")
public interface EnderecoFeing {
@GetMapping("/{cep}/json")
Endereco enderecoAPI(@PathVariable("cep") String cep);

}
