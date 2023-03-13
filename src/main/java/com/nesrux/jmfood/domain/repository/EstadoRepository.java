package com.nesrux.jmfood.domain.repository;

import java.util.List;

import com.nesrux.jmfood.domain.model.Estado;

public interface EstadoRepository {

	Estado buscar(Long id);

	Estado salvar(Estado estado);

	void remover(Estado estado);

	List<Estado> listar();

}
