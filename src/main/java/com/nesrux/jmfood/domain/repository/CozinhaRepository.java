package com.nesrux.jmfood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nesrux.jmfood.domain.model.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	List<Cozinha> findLikeByNome(String nome);

}
