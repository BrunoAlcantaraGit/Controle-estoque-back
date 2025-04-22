package com.controle.estoque.repository;

import com.controle.estoque.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendasRepository extends JpaRepository<Venda,Long>{

    Optional<Venda>findByCodigo(String codigo);
}
