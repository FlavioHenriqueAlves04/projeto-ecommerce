package com.mystore.projeto_ecommerce.domain.service;

import com.mystore.projeto_ecommerce.domain.entity.Produto;
import com.mystore.projeto_ecommerce.domain.exception.ProdutoNaoEncontradoException;
import com.mystore.projeto_ecommerce.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Adcionar produto
    public Produto adicionarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    // Buscar todos os produtos
    public List<Produto> buscarProdutos(){
        return produtoRepository.findAll();
    }

    // Buscar produto pelo id
    public Produto buscarProdutoPorId(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));
    }

    // Atualizar produto
    public Produto atualizarProduto(Integer id, Produto produtoAtualizado) {
        Produto produto = buscarProdutoPorId(id);
        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setPreco(produtoAtualizado.getPreco());
        return produtoRepository.save(produto);
    }

    // Excluir Produto
    public void excluirProduto(Integer id){
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        } else {
            throw new ProdutoNaoEncontradoException("Produto não encontrado");
        }
    }


}
