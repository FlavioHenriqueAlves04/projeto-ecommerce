package com.mystore.projeto_ecommerce.domain.repository;


import com.mystore.projeto_ecommerce.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
