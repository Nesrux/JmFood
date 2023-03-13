package com.nesrux.jmfood.domain.repository;

import java.util.List;

import com.nesrux.jmfood.domain.model.Permissao;
import com.nesrux.jmfood.domain.model.Restaurante;

public interface PermissaoRepository {
	List<Permissao> listar();

	Restaurante salvar(Restaurante restaurante);

	void remover(Restaurante restaurante);

	Restaurante buscar(Long id);

}
