package com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(Long cozinhaId) {
		this(String.format("A Cozinha de código %d não foi encontrada", cozinhaId));
	}

}
