package com.nesrux.jmfood.domain.exception.negocioException;

import com.nesrux.jmfood.domain.exception.NegocioException;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}
