package com.nesrux.jmfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nesrux.jmfood.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	
}
