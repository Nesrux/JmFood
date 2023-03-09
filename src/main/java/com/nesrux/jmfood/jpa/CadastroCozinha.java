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
	private EntityManager menager;

	public List<Cozinha> listar() {
		return menager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return menager.merge(cozinha);
	}

	public Cozinha buscar(Long id) {
		return menager.find(Cozinha.class, id);
		/*
		 * Oque ele vai fazer nesse caso é um SELECT * from Cozinha where ID
		 */
	}

	@Transactional
	public void remover(Cozinha cozinha) {
		cozinha = buscar(cozinha.getId());
		menager.remove(cozinha);
	}
}
