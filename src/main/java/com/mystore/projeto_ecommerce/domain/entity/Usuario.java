package com.mystore.projeto_ecommerce.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Email
    @Column(unique = true)
    private String email;

    private String senha;

    private String endereco;

    private String telefone;

    private String tipo; // ADMIN, CLIENTE

}
