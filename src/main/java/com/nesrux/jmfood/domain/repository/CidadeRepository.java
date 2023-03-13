package com.nesrux.jmfood.domain.repository;

import java.util.List;

import com.nesrux.jmfood.domain.model.Cidade;

public interface CidadeRepository {
	List<Cidade> listar();

	Cidade salvar(Cidade Cidade);

	void remover(Cidade Cidade);

	Cidade buscar(Long id);
}
