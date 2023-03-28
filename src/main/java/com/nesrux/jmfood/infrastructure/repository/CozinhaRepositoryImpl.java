package com.nesrux.jmfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.model.Cozinha;
import com.nesrux.jmfood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager menager;

	public List<Cozinha> listar() {
		return menager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	@Override
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return menager.merge(cozinha);
	}

	@Override
	public Cozinha buscar(Long id) {
		return menager.find(Cozinha.class, id);
	}

	@Override
	@Transactional
	public void remover(Long cozinhaId) {
		// Ele só funciona assim , pois existem varios estados de beans no Spring//
		Cozinha cozinha = buscar(cozinhaId);
		
		if(cozinha == null ) {
			throw new EmptyResultDataAccessException(1);
		}
		
		menager.remove(cozinha);
	}

}
