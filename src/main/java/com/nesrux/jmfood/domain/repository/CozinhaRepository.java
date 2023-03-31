package com.nesrux.jmfood.domain.repository;

import java.util.List;

import com.nesrux.jmfood.domain.model.Cozinha;

public interface CozinhaRepository {
	// Repositório orientado a persistencia
	Cozinha buscar(Long id);

	Cozinha salvar(Cozinha cozinha);
	
	List<Cozinha> buscarNome (String nome);

	void remover(Long id);

	List<Cozinha> listar();

}
