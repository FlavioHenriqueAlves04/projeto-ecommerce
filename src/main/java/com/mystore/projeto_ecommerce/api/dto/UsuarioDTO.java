package com.mystore.projeto_ecommerce.api.dto;

import com.mystore.projeto_ecommerce.domain.entity.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private String endereco;
    private String telefone;
    private String tipo;

    // Método para converter DTO para Entidade Usuario
    public Usuario toUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        usuario.setEndereco(this.endereco);
        usuario.setTelefone(this.telefone);
        usuario.setTipo(this.tipo);
        return usuario;
    }
}
