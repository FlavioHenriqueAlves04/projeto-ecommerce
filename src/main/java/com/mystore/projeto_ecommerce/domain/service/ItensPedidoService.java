package com.mystore.projeto_ecommerce.domain.service;
import com.mystore.projeto_ecommerce.api.dto.ItensPedidoDTO;
import com.mystore.projeto_ecommerce.domain.entity.ItensPedido;
import com.mystore.projeto_ecommerce.domain.entity.Produto;
import com.mystore.projeto_ecommerce.domain.exception.EstoqueInsuficienteException;
import com.mystore.projeto_ecommerce.domain.exception.ItensPedidoNaoEncontradoException;
import com.mystore.projeto_ecommerce.domain.exception.ProdutoNaoEncontradoException;
import com.mystore.projeto_ecommerce.domain.repository.ItensPedidoRepository;
import com.mystore.projeto_ecommerce.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
@Service
public class ItensPedidoService {
    private final ProdutoRepository produtoRepository;
    private final ItensPedidoRepository itensPedidoRepository;
    public ItensPedidoService(ProdutoRepository produtoRepository, ItensPedidoRepository itensPedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.itensPedidoRepository = itensPedidoRepository;
    }
    public ItensPedido criarItemPedido(ItensPedidoDTO itensPedidoDTO) {
        // Buscar o produto no banco de dados
        Produto produto = produtoRepository.findById(itensPedidoDTO.getProdutoId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));
        // Verificar se há estoque suficiente
        if (produto.getEstoque() < itensPedidoDTO.getQuantidade()) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + produto.getNome());
        }
        // Calcular o subtotal corretamente
        BigDecimal subtotal = produto.getPreco().multiply(BigDecimal.valueOf(itensPedidoDTO.getQuantidade()));
        // Criar o objeto ItensPedido e associar o produto
        ItensPedido itensPedido = new ItensPedido();
        itensPedido.setProduto(produto);
        itensPedido.setQuantidade(itensPedidoDTO.getQuantidade());
        itensPedido.setSubtotal(subtotal);
        // Diminuir o estoque do produto
        produto.setEstoque(produto.getEstoque() - itensPedidoDTO.getQuantidade());
        produtoRepository.save(produto);  // Atualiza o estoque no banco de dados
        // Salvar o item do pedido no banco de dados
        return itensPedidoRepository.save(itensPedido);
    }
}
