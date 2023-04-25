package com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;

public abstract class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	private static final long serialVersionUID = 1L;

}
