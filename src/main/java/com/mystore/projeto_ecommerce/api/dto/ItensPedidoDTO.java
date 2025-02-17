package com.mystore.projeto_ecommerce.api.dto;

import com.mystore.projeto_ecommerce.domain.entity.ItensPedido;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ItensPedidoDTO {

    private Integer produtoId;
    private Integer quantidade;
    private BigDecimal subtotal;

    // Construtor com argumentos
    public ItensPedidoDTO(Integer produtoId, Integer quantidade, BigDecimal subtotal) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
    }

    // Método para converter de ItensPedido para ItensPedidoDTO
    public static ItensPedidoDTO fromItensPedido(ItensPedido itemPedido) {
        return new ItensPedidoDTO(
                itemPedido.getProduto().getId(),
                itemPedido.getQuantidade(),
                itemPedido.getSubtotal()
        );
    }
}
