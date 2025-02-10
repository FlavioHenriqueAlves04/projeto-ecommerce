package com.mystore.projeto_ecommerce.domain.repository;


import com.mystore.projeto_ecommerce.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
