package com.mystore.projeto_ecommerce.api.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProdutoDTO {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer estoque;
    private String categoria;

}
