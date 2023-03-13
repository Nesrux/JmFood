package com.nesrux.jmfood.domain.repository;

import java.util.List;

import com.nesrux.jmfood.domain.model.Estado;

public interface EstadoRepository {
	List<Estado> listar();

	Estado salvar(Estado estado);

	void remover(Estado estado);

	Estado buscar(Long id);
}
