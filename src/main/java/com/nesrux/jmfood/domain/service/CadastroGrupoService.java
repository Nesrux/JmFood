package com.nesrux.jmfood.domain.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeEmUsoException;
import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.GrupoNaoEncontradoException;
import com.nesrux.jmfood.domain.model.user.Grupo;
import com.nesrux.jmfood.domain.model.user.Permissao;
import com.nesrux.jmfood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {
	@Autowired
	private GrupoRepository repository;

	@Autowired
	private CadastroPermissaoService permissaoService;

	public List<Grupo> acharTodos() {
		return repository.findAll();
	}

	public Grupo acharOuFalahar(Long grupoId) {
		return repository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return repository.save(grupo);
	}

	public void excluir(Long grupoId) {
		try {
			repository.deleteById(grupoId);
			// repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("O grupo de código %d esta em uso", grupoId));
		}
	}

	public List<Permissao> listarPermissoes(Long grupoId) {
		Grupo grupo = acharOuFalahar(grupoId);

		return grupo.getPermissoes().stream().toList();
	}
	
	//Caso dee ero :C
	public Set<Permissao> permissoesListar(Long grupoId){
		Grupo grupo = acharOuFalahar(grupoId);
		return  grupo.getPermissoes();
	}

	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = acharOuFalahar(grupoId);
		Permissao permissao = permissaoService.acharOuFalhar(permissaoId);

		grupo.associarPermissao(permissao);
	}

	@Transactional
	public void deassociarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = acharOuFalahar(grupoId);
		Permissao permissao = permissaoService.acharOuFalhar(permissaoId);

		grupo.deassociarPermissao(permissao);
	}
}
