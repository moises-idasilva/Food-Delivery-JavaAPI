package com.moises.foodapp.domain.exception;

public class CozihaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CozihaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CozihaNaoEncontradaException(Long id) {
        this(String.format("Não existe um cadastro de Cozinha com ID:  %d", id));
    }


}

