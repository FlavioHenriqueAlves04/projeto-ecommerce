package com.mystore.projeto_ecommerce.domain.repository;


import com.mystore.projeto_ecommerce.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    UserDetails FindyByLogin(String login);
}
