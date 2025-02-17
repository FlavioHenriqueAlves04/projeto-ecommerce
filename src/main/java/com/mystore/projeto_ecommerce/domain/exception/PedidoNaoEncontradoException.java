package com.mystore.projeto_ecommerce.domain.exception;

public class PedidoNaoEncontradoException extends RuntimeException{

    public PedidoNaoEncontradoException(String message){
        super(message);
    }
}
