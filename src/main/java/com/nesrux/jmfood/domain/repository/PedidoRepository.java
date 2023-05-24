package com.nesrux.jmfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.nesrux.jmfood.domain.model.pedido.Pedido;

public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> , JpaSpecificationExecutor<Pedido>{
	@Query("FROM Pedido p JOIN fetch p.cliente JOIN fetch p.restaurante r join fetch r.cozinha")
	List<Pedido> findAll();

	Optional<Pedido> findByCodigo(String codigo);
}
