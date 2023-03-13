package com.nesrux.jmfood.domain.repository;

import java.util.List;

import com.nesrux.jmfood.domain.model.Permissao;

public interface PermissaoRepository {
	Permissao salvar(Permissao permissao);

	Permissao buscar(Long id);

	List<Permissao> listar();

	void remover(Permissao permissao);

}
