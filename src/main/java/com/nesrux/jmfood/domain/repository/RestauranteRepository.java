package com.nesrux.jmfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.nesrux.jmfood.domain.model.endereco.Endereco;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, CustomizedRestauranteRepository,
		JpaSpecificationExecutor<Restaurante> {
	// alem do prefixo find, existe também o query, stream, get que funcionam
	// exatamente igual
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	// Só vai funcionar assim pq tem um arquivo dentro da pasta Meta-Inf que faz o
	// bind desse método com a consulta que ele tem que fazer
	List<Restaurante> consultarPorNome(String nome, Long id);

	@Query("from Restaurante r join fetch r.cozinha") // left join fetch r.formasPagamento <- para fazer o join com
														// formas de pagamento!
	List<Restaurante> findAll();

//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

	Optional<Restaurante> findFristRestauranteByNomeContaining(String nome);

	List<Restaurante> findTop2RestauranteByNomeContaining(String nome);

	int countByCozinhaId(Long cozinhaId);

	// Para adicionar a paginação de um método implementado pelo jpa ele precisa
	// retornar page e implementar o peagleble
	Page<Restaurante> findByEndereco(Endereco endereco, Pageable pageable);

	//@Query("select case when count(1) > 0 then true else false end from Restaurante rest\r\n join rest.responsaveis resp\r\n where rest.id = :restauranteId	and resp.id = :usuarioId")
	boolean existsResponsavel(Long restauranteId, Long usuarioId);

}