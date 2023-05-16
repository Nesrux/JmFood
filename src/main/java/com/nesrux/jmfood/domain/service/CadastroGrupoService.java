package com.nesrux.jmfood.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeEmUsoException;
import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.GrupoNaoEncontradoException;
import com.nesrux.jmfood.domain.model.user.Grupo;
import com.nesrux.jmfood.domain.repository.GrupoRepository;

@Component
public class CadastroGrupoService {
	@Autowired
	private GrupoRepository repository;

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
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("O grupo de código %d esta em uso", grupoId));
		}
	}
}
