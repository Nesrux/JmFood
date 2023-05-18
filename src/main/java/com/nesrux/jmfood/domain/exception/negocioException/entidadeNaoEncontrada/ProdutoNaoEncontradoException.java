package com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ProdutoNaoEncontradoException(Long produtoId, Long restauranteid) {
		this(String.format("Não existe um produto cadastrado de Id %d cadastrado no restaurante de Id %d", produtoId,
				restauranteid));
	}

	public ProdutoNaoEncontradoException(Long produtoId) {
		this(String.format("Não existe um produto cadastrado de Id %d", produtoId));
	}
}
