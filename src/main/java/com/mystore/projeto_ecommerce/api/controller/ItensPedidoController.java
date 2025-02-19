package com.mystore.projeto_ecommerce.api.controller;

import com.mystore.projeto_ecommerce.api.dto.ItensPedidoDTO;
import com.mystore.projeto_ecommerce.domain.entity.ItensPedido;
import com.mystore.projeto_ecommerce.domain.service.ItensPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/itens-pedido")
public class ItensPedidoController {

    private final ItensPedidoService itensPedidoService;

    public ItensPedidoController(ItensPedidoService itensPedidoService) {
        this.itensPedidoService = itensPedidoService;
    }

    @PostMapping
    public ResponseEntity<ItensPedido> criarItemPedido(@RequestBody ItensPedidoDTO itensPedidoDTO) {
        ItensPedido itensPedido = itensPedidoService.criarItemPedido(itensPedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(itensPedido);
    }
}
