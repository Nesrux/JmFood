package com.nesrux.jmfood.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.nesrux.jmfood.domain.model.pedido.FotoProduto;
import com.nesrux.jmfood.domain.repository.ProdutoRepositoryQueries;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public FotoProduto save(FotoProduto foto) {
		return manager.merge(foto);
	}

}
