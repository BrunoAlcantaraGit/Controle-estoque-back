package com.controle.estoque.repository;

import com.controle.estoque.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco,Long> {

Optional<Endereco>findBycep(String cep);
}
