package com.nesrux.jmfood.infrastructure.repository.spec;

import java.math.BigDecimal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.nesrux.jmfood.domain.model.restaurante.Restaurante;

public class RestauranteComFreteGratisSpec implements Specification<Restaurante> {

	private static final long serialVersionUID = 1L;

	@Override
	public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		return builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
}
