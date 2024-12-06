package com.controle.estoque.repository;

import com.controle.estoque.model.Cliente;
import feign.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
Optional<Cliente>findByDocumento(String documento);
}
