package com.nesrux.jmfood.domain.repository;

import java.util.List;

import com.nesrux.jmfood.domain.model.Permissao;

public interface PermissaoRepository {
	List<Permissao> listar();

	Permissao salvar(Permissao permissao);

	void remover(Permissao permissao);

	Permissao buscar(Long id);

}
