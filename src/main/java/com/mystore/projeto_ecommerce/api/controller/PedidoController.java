package com.mystore.projeto_ecommerce.api.controller;

import com.mystore.projeto_ecommerce.domain.entity.Pedido;
import com.mystore.projeto_ecommerce.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido){
        return pedidoService.criarPedido(pedido);
    }

    @GetMapping
    public List<Pedido> buscarPedidos(){
        return pedidoService.buscarPedidos();
    }

    @GetMapping("/{id}")
    public Optional<Pedido> buscarPedidoPorId(@PathVariable Integer id){
        return pedidoService.buscarPedidoPorId(id);
    }

    @PutMapping("/{id}/status")
    public Pedido atualizarStatus(@PathVariable Integer id, @RequestParam String status) {
        return pedidoService.atualizarStatusPedido(id, status);
    }
}
