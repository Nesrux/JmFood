package com.nesrux.jmfood.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.nesrux.jmfood.domain.filter.PedidoFilter;
import com.nesrux.jmfood.domain.model.pedido.Pedido;

public class PedidoSpecs {

	public static Specification<Pedido> usandoFiltro(PedidoFilter filter) {
		return (root, query, builder) -> {
			if (Pedido.class.equals(query.getResultType())) {
				root.fetch("restaurante").fetch("cozinha");
				root.fetch("cliente");
			}
			var predicates = new ArrayList<>();

			if (filter.getClienteId() != null) {
				predicates.add(builder.equal(root.get("cliente"), filter.getClienteId()));
			}

			if (filter.getRestauranteId() != null) {
				predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
			}

			if (filter.getDataCriacao() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacao()));
			}
			if (filter.getDataFinalizacao() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filter.getDataFinalizacao()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
