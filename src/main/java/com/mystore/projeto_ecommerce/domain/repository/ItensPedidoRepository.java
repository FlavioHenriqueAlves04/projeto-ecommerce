package com.mystore.projeto_ecommerce.domain.repository;


import com.mystore.projeto_ecommerce.domain.entity.ItensPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedidoRepository extends JpaRepository<ItensPedido,Integer> {
}
