package com.mystore.projeto_ecommerce.domain.exception;

public class ProdutoNaoEncontradoException extends RuntimeException{

    public ProdutoNaoEncontradoException(String message){
        super(message);
    }
}
