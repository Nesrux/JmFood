package com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;

public abstract class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format("Estado com o código %d não foi encontrado", estadoId));
	}

}
