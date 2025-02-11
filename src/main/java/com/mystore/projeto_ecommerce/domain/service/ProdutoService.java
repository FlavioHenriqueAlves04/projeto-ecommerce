package com.mystore.projeto_ecommerce.domain.service;


import com.mystore.projeto_ecommerce.domain.entity.Produto;
import com.mystore.projeto_ecommerce.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Adcionar produto
    public Produto adcionarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    // Buscar todos os produtos
    public List<Produto> buscarProdutos(){
        return produtoRepository.findAll();
    }

    // Buscar produto pelo id
    public Optional<Produto> buscarProdutoPorId(Integer id){
        return produtoRepository.findById(id);
    }

    // Atualizar produto
    public Produto atualizarProduto(Integer id,Produto produto){
        if (produtoRepository.existsById(id)){
            produto.setId(id);
            return produtoRepository.save(produto);
        }
        throw new RuntimeException("Produto não encontrado");
    }

    // Excluir Produto
    public void excluirProduto(Integer id){
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Produto não encontrado");
        }
    }


}
