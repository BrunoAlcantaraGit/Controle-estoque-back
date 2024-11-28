package com.controle.estoque.repository;

import com.controle.estoque.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor,Long> {

    Optional<Fornecedor>findBycnpj(String cnpj);

}
