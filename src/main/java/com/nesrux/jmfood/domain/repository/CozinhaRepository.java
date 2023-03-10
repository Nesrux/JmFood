package com.nesrux.jmfood.domain.repository;

import java.util.List;

import com.nesrux.jmfood.domain.model.Cozinha;

public interface CozinhaRepository {
	//Repositório orientado a persistencia
	List<Cozinha> listar();

	Cozinha buscar(Long id);

	Cozinha salvar(Cozinha cozinha);

	void remover(Cozinha cozinha);
}
