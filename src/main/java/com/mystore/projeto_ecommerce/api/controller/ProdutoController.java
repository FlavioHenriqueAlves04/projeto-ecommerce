package com.mystore.projeto_ecommerce.api.controller;

import com.mystore.projeto_ecommerce.domain.entity.Produto;
import com.mystore.projeto_ecommerce.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public Produto adcionarProduto(@RequestBody Produto produto){
        return produtoService.adcionarProduto(produto);
    }

    @GetMapping
    public List<Produto> buscarProdutos(){
        return produtoService.buscarProdutos();
    }

    @GetMapping("/{id}")
    public Optional<Produto> buscarProdutoPorId(@PathVariable Integer id){
        return produtoService.buscarProdutoPorId(id);
    }

    @PutMapping("/{id}")
    public  Produto atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto ){
        return produtoService.atualizarProduto(id,produto);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Integer id){
        produtoService.excluirProduto(id);
    }
}
