package com.mystore.projeto_ecommerce.domain.service;

import com.mystore.projeto_ecommerce.api.dto.PedidoDTO;
import com.mystore.projeto_ecommerce.domain.entity.*;
import com.mystore.projeto_ecommerce.domain.exception.EstoqueInsuficienteException;
import com.mystore.projeto_ecommerce.domain.exception.PedidoNaoEncontradoException;
import com.mystore.projeto_ecommerce.domain.exception.ProdutoNaoEncontradoException;
import com.mystore.projeto_ecommerce.domain.exception.UsuarioNaoEncontradoException;
import com.mystore.projeto_ecommerce.domain.repository.PedidoRepository;
import com.mystore.projeto_ecommerce.domain.repository.ProdutoRepository;
import com.mystore.projeto_ecommerce.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Criar pedido
    @Transactional
    public Pedido criarPedido(PedidoDTO pedidoDTO) {
        Usuario cliente = usuarioRepository.findById(pedidoDTO.getClienteId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Cliente não encontrado"));

        Pedido pedido = pedidoDTO.toPedido(cliente);

        List<ItensPedido> itensPedido = pedidoDTO.getItens().stream().map(itemDTO -> {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

            if (produto.getEstoque() < itemDTO.getQuantidade()) {
                throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.setEstoque(produto.getEstoque() - itemDTO.getQuantidade());
            produtoRepository.save(produto);

            ItensPedido item = new ItensPedido();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.calcularSubtotal(); // Calcula o subtotal para o item
            item.setPedido(pedido); // Garante que o item está associado ao pedido
            return item;
        }).collect(Collectors.toList());

        pedido.setItens(itensPedido);
        pedido.calcularTotal(); // Calcula o total do pedido com os itens

        return pedidoRepository.save(pedido); // Salva o pedido e seus itens
    }

    // Buscar todos os pedidos
    public List<Pedido> buscarPedidos() {
        return pedidoRepository.findAll();
    }

    // Buscar pedido pelo id
    public Pedido buscarPedidoPorId(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado"));
    }

    // Atualizar Status do pedido
    public Pedido atualizarStatusPedido(Integer id, StatusPedido status) {
        Pedido pedido = buscarPedidoPorId(id);

        if (status == null) {
            throw new IllegalArgumentException("Status do pedido não pode ser nulo.");
        }

        pedido.setStatus(status);
        return pedidoRepository.save(pedido);
    }
}
