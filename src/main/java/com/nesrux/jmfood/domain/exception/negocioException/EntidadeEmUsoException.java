package com.nesrux.jmfood.domain.exception.negocioException;

import com.nesrux.jmfood.domain.exception.NegocioException;

public class EntidadeEmUsoException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}

}
