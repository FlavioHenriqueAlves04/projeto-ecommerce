package com.mystore.projeto_ecommerce.api.controller;

import com.mystore.projeto_ecommerce.api.dto.ProdutoDTO;
import com.mystore.projeto_ecommerce.domain.entity.Produto;
import com.mystore.projeto_ecommerce.domain.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = new Produto(produtoDTO.getNome(), produtoDTO.getDescricao(), produtoDTO.getPreco(),produtoDTO.getEstoque(),produtoDTO.getCategoria());
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.adicionarProduto(produto));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> buscarProdutos() {
        return ResponseEntity.ok(produtoService.buscarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDTO) {
        Produto produtoAtualizado = new Produto(produtoDTO.getNome(), produtoDTO.getDescricao(), produtoDTO.getPreco(), produtoDTO.getEstoque(), produtoDTO.getCategoria());
        return ResponseEntity.ok(produtoService.atualizarProduto(id, produtoAtualizado));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
        produtoService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }
}
