package com.controle.estoque.repository;

import com.controle.estoque.model.SaidaDeProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaidaDeProdutoRepository extends JpaRepository<SaidaDeProduto,Long> {
}
