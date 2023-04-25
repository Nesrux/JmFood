package com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;

public abstract class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
		}

}
