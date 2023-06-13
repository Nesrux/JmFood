package com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;

public class FotoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FotoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public FotoNaoEncontradaException(Long restauranteId, Long produtoId) {
		this(String.format("Não Existe uma cadastro de Foto com id %d no Restaurante de Id %d", produtoId,
				restauranteId));
	}

}
