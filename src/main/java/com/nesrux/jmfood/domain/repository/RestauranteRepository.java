package com.nesrux.jmfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.nesrux.jmfood.domain.model.Restaurante;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, CustomizedRestauranteRepository, JpaSpecificationExecutor<Restaurante> {
	// alem do prefixo find, existe também o query, stream, get que funcionam
	// exatamente igual
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	//Só vai funcionar assim pq tem um arquivo dentro da pasta Meta-Inf que faz o bind desse método com a consulta que ele tem que fazer 
	List <Restaurante> acharPorNomeEId(String nome, Long id);
	
//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

	Optional<Restaurante> findFristRestauranteByNomeContaining(String nome);

	List<Restaurante> findTop2RestauranteByNomeContaining(String nome);

	int countByCozinhaId(Long cozinhaId);
	

}
