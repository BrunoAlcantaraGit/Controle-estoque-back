package com.controle.estoque.repository;
import com.controle.estoque.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface VendasRepository extends JpaRepository<Venda,Long>{


}
