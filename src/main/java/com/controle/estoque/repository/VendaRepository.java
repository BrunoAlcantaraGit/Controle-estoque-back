package com.controle.estoque.repository;

import com.controle.estoque.model.Orcamento;
import com.controle.estoque.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda,Long> {
}
