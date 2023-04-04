package com.nesrux.jmfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nesrux.jmfood.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	// alem do prefixo find, existe também o query, stream, get que funcionam
	// exatamente igual
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List <Restaurante> acharPorNomeEId(String nome, Long id);
	
//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

	Optional<Restaurante> findFristRestauranteByNomeContaining(String nome);

	List<Restaurante> findTop2RestauranteByNomeContaining(String nome);

	int countByCozinhaId(Long cozinhaId);

}
