package com.nesrux.jmfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.PermissaoNaoEncontradaException;
import com.nesrux.jmfood.domain.model.user.Permissao;
import com.nesrux.jmfood.domain.repository.PermissaoRepository;

@Component
public class CadastroPermissaoService {
	@Autowired
	private PermissaoRepository repository;

	public Permissao acharOuFalhar(Long permissaoId) {
		return repository.findById(permissaoId).orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}

}
