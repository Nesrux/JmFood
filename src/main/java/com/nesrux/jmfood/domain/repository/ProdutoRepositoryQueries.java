package com.nesrux.jmfood.domain.repository;

import com.nesrux.jmfood.domain.model.pedido.FotoProduto;

public interface ProdutoRepositoryQueries {

	FotoProduto save(FotoProduto save);
	void delete(FotoProduto foto);
}
