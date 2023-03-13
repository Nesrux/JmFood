package com.nesrux.jmfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nesrux.jmfood.domain.model.Restaurante;
import com.nesrux.jmfood.domain.repository.RestauranteRepository;

public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager menager;

	@Override
	public List<Restaurante> listar() {
		return menager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante buscar(Long id) {
		return menager.find(Restaurante.class, id);
	}

	@Override
	public Restaurante salvar(Restaurante restaurante) {
		return menager.merge(restaurante);
	}

	@Override
	public void remover(Restaurante restaurante) {
		restaurante = buscar(restaurante.getId());
		menager.remove(restaurante);
	}

}
