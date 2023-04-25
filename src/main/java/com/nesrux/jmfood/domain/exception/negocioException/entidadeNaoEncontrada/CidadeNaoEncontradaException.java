package com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(Long cidadeId) {
		this(String.format("Cidade com o código %d não foi encontrado", cidadeId));
	}
}
