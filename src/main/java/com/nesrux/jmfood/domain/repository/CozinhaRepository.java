package com.nesrux.jmfood.domain.repository;

import java.util.List;
import java.util.Optional;

import com.nesrux.jmfood.domain.model.restaurante.Cozinha;

public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

	List<Cozinha> findByNomeContaining(String nome);

	Optional<Cozinha> findByNome(String nome);

	boolean existsByNome(String nome);


}
