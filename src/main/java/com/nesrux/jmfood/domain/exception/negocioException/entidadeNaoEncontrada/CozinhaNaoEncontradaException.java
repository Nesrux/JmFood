package com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;

public abstract class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	private static final long serialVersionUID = 1L;

}
