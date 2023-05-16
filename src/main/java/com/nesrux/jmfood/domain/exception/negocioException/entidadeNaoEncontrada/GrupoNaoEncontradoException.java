package com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public GrupoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public GrupoNaoEncontradoException(Long id) {
		this(String.format("o Grupo com o ID %d, não foi encontrado", id));
	}
}
