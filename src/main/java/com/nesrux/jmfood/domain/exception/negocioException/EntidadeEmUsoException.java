package com.nesrux.jmfood.domain.exception.negocioException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nesrux.jmfood.domain.exception.NegocioException;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}

}
