package com.nesrux.jmfood.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.nesrux.jmfood.domain.model.pedido.Pedido;
import com.nesrux.jmfood.domain.repository.filter.PedidoFilter;

public class PedidoSpecs {

	public static Specification<Pedido> usandoFiltro(PedidoFilter filter){
		return (root, query, builder) -> {
			var predicates = new ArrayList<>();
			
			if(filter.getClienteId() != null) {
				predicates.add(builder.equal(root.get("cliente") , filter.getClienteId()));
			}
			
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
		}
}
