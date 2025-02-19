package com.mystore.projeto_ecommerce.domain.entity;

import lombok.Getter;

@Getter
public enum UsuarioTipo {
    ADMIN("admin"),
    USER("user");

    private String tipo;

    UsuarioTipo(String tipo) {
        this.tipo = tipo;
    }
}
