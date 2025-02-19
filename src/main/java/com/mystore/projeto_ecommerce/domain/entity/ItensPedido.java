package com.mystore.projeto_ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
@Data
public class ItensPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Produto produto;

    private Integer quantidade;
    private BigDecimal subtotal;
}
dadsasd