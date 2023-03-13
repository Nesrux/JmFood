package com.nesrux.jmfood.domain.repository;

import java.util.List;

import com.nesrux.jmfood.domain.model.Restaurante;

public interface RestauranteRepository {
	// Repositório orientado a persistencia
	List<Restaurante> listar();

	Restaurante buscar();

	Restaurante salvar();

	void remover();

}
