package com.nesrux.jmfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.PermissaoNaoEncontradaException;
import com.nesrux.jmfood.domain.model.user.Grupo;
import com.nesrux.jmfood.domain.model.user.Permissao;
import com.nesrux.jmfood.domain.repository.PermissaoRepository;

@Component
public class CadastroPermissaoService {
	@Autowired
	private PermissaoRepository repository;
	
	@Autowired
	private CadastroGrupoService grupoService;
	
	public Permissao acharOuFalhar(Long permissaoId) {
		return repository.findById(permissaoId).orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}
	
	public Permissao acharOuFalhar(Long grupoId, Long permissaoId) {
		Grupo grupo = grupoService.acharOuFalahar(grupoId);
		Long idGrupo = grupo.getId();
	
		return repository.findById(permissaoId, idGrupo)
				.orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId, idGrupo));
	}


}
