package com.mystore.projeto_ecommerce.api.controller;

import com.mystore.projeto_ecommerce.api.dto.PedidoDTO;
import com.mystore.projeto_ecommerce.domain.entity.Pedido;
import com.mystore.projeto_ecommerce.domain.entity.StatusPedido;
import com.mystore.projeto_ecommerce.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.criarPedido(pedidoDTO);

        // Convertendo para DTO e retornando
        PedidoDTO pedidoResponse = PedidoDTO.fromPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoResponse);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> buscarPedidos() {
        return ResponseEntity.ok(pedidoService.buscarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoService.buscarPedidoPorId(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable Integer id, @RequestBody StatusPedido status) {
        return ResponseEntity.ok(pedidoService.atualizarStatusPedido(id, status));
    }
}
