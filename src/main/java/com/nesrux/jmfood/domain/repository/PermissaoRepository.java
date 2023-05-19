package com.nesrux.jmfood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nesrux.jmfood.domain.model.user.Permissao;

public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {
	
	@Query("from Permissao where grupo.id = :grupo and permissao.id = :permissao")
	Optional<Permissao> findById(@Param("grupo") Long grupoId, @Param("permissao") Long permissaoId);
}
