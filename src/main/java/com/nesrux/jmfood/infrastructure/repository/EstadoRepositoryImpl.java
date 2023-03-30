package com.nesrux.jmfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.model.Estado;
import com.nesrux.jmfood.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Estado buscar(Long id) {

		return manager.find(Estado.class, id);
	}

	@Override
	@Transactional
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}

	@Override
	@Transactional
	public void remover(Estado estado) {
		estado = buscar(estado.getId());
		manager.remove(estado);
	}

	@Override
	public List<Estado> listar() {
		return manager.createQuery("from Estado", Estado.class).getResultList();
	}

}
