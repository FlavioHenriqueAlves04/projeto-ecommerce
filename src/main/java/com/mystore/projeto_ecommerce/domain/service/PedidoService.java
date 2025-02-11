package com.mystore.projeto_ecommerce.domain.service;

import com.mystore.projeto_ecommerce.domain.entity.Pedido;
import com.mystore.projeto_ecommerce.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // Criar pedido
    public Pedido criarPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    // Buscar todos os pedidos
    public List<Pedido> buscarPedidos(){
        return pedidoRepository.findAll();
    }

    // Buscar pedido pelo id
    public Optional<Pedido> buscarPedidoPorId(Integer id){
        return pedidoRepository.findById(id);
    }

    // Atualizar Status do pedido
    public Pedido atualizarStatusPedido(Integer id, String status){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus(status);
        return pedidoRepository.save(pedido);
    }
}
