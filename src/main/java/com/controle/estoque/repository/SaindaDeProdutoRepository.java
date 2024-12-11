package com.controle.estoque.repository;

import com.controle.estoque.model.SaídaDeProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaindaDeProdutoRepository extends JpaRepository<SaídaDeProduto,Long> {
}
