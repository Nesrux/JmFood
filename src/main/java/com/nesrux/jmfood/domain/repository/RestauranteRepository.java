package com.nesrux.jmfood.domain.repository;

import java.util.List;

import com.nesrux.jmfood.domain.model.Restaurante;

public interface RestauranteRepository {
	// Repositório orientado a persistencia
	Restaurante buscar(Long id);

	Restaurante salvar(Restaurante restaurante);

	List<Restaurante> listar();

	void remover(Restaurante restaurante);

}
