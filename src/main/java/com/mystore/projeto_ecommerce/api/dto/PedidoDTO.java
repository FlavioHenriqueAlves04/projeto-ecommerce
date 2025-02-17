package com.mystore.projeto_ecommerce.api.dto;

import com.mystore.projeto_ecommerce.domain.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PedidoDTO {

    private Integer clienteId;
    private List<ItensPedidoDTO> itens;
    private BigDecimal total;
    private StatusPedido status;

    // Método para converter Pedido em PedidoDTO
    public static PedidoDTO fromPedido(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setClienteId(pedido.getCliente().getId());
        dto.setStatus(pedido.getStatus());
        dto.setTotal(pedido.getTotal());

        // Converter os itens do pedido (usando o método fromItensPedido)
        List<ItensPedidoDTO> itensDTO = pedido.getItens().stream()
                .map(ItensPedidoDTO::fromItensPedido)
                .collect(Collectors.toList());

        dto.setItens(itensDTO);
        return dto;
    }

    // Método para converter PedidoDTO em Pedido
    public Pedido toPedido(Usuario usuario) {
        Pedido pedido = new Pedido();
        pedido.setTotal(this.total);
        pedido.setStatus(this.status);
        pedido.setCliente(usuario); // Associando o cliente (usuario)

        // Convertendo os itens do pedido
        List<ItensPedido> itens = this.itens.stream()
                .map(itensPedidoDTO -> {
                    ItensPedido item = new ItensPedido();
                    item.setQuantidade(itensPedidoDTO.getQuantidade());
                    item.setSubtotal(itensPedidoDTO.getSubtotal());

                    // Associando o produto
                    Produto produto = new Produto();
                    produto.setId(itensPedidoDTO.getProdutoId());
                    item.setProduto(produto);

                    return item;
                })
                .collect(Collectors.toList());

        pedido.setItens(itens);
        return pedido;
    }
}
