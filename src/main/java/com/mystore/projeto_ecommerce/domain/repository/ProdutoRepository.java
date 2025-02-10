package com.mystore.projeto_ecommerce.domain.repository;


import com.mystore.projeto_ecommerce.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
