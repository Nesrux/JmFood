package com.nesrux.jmfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nesrux.jmfood.domain.model.pedido.Produto;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;

public interface ProdutoRepository extends CustomJpaRepository<Produto, Long> {

	List<Produto> findByRestaurante(Restaurante restaurante);// Retorna os produtos de restaurante

	@Query("from Produto where restaurante.id = :restaurante and id = :produto")
	Optional<Produto> findById(@Param("restaurante") Long restauranteId, @Param("produto") Long produtoId);

	@Query("FROM Produto p WHERE p.ativo = true AND p.restaurante = :restaurante")
	List<Produto> findAtivosByRestaurante(Restaurante restaurante);
	

}
