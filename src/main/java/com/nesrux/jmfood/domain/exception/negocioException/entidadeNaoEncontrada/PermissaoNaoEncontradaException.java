package com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public PermissaoNaoEncontradaException(Long persmissaoId) {
		this(String.format("Não foi possivel encontrar uma permissão com o Id %d", persmissaoId));
	}

	public PermissaoNaoEncontradaException(Long persmissaoId, Long grupoId) {
		this(String.format("Não foi possivel encontrar uma permissão com o Id %d no grupo de id %d", persmissaoId,
				grupoId));
	}

}
