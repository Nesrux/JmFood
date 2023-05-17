package com.nesrux.jmfood.domain.exception.negocioException;

import com.nesrux.jmfood.domain.exception.NegocioException;

public class SenhaInvaldaException extends NegocioException {
	private static final long serialVersionUID = 1L;
	private static final String MSG_SENHA_INVALIDA = "A senha atual esta inválida, por favor preencha corretamente e tente de novo.";

	public SenhaInvaldaException(String mensagem) {
		super(mensagem);
	}

	public SenhaInvaldaException() {
		this(MSG_SENHA_INVALIDA);
	}

	public SenhaInvaldaException(Long id) {
		this(String.format("Você não pode cadastrar a mesma senha mais de uma vez"));
	}

}
