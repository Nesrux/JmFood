package com.nesrux.jmfood.domain.exception.spec;

import com.nesrux.jmfood.domain.exception.EntidadeNaoEncontradaException;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format("Estado com o código %d não foi encontrado", estadoId));
	}

}
