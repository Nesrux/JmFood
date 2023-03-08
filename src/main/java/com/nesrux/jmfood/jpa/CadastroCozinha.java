package com.nesrux.jmfood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.model.Cozinha;

@Component
public class CadastroCozinha {

	@PersistenceContext // Injeção de persistencia do próprio JPA
	private EntityManager manager;

	public List<Cozinha> listar() {
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	@Transactional
	public Cozinha adicionar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
}
