package com.mystore.projeto_ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItensPedido> itens;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @PrePersist
    @PreUpdate
    public void calcularTotal() {
        this.total = itens.stream()
                .map(ItensPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
