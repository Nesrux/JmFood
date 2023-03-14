package com.nesrux.jmfood.domain.repository;

import java.util.List;

import com.nesrux.jmfood.domain.model.Cidade;

public interface CidadeRepository {
	Cidade salvar(Cidade Cidade);

	
	Cidade buscar(Long id);

	List<Cidade> listar();

	void remover(Cidade Cidade);

}
