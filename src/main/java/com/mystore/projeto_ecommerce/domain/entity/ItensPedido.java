package com.mystore.projeto_ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "itens_pedido")
public class ItensPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Produto produto;

    private Integer quantidade;

    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "pedido_id")  // Adiciona o relacionamento com Pedido
    private Pedido pedido;  // Campo que irá associar o pedido ao item

    @PrePersist
    @PreUpdate
    public void calcularSubtotal() {
        if (produto != null && quantidade != null) {
            this.subtotal = produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
        }
    }

}
