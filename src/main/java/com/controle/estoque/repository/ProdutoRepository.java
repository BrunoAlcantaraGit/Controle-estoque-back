package com.controle.estoque.repository;
import com.controle.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    Optional<Produto> findBycodigo(String codigo);

}
