package com.nesrux.jmfood.api.v1.model.dto.output.restaurante;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "restaurantes")
public class RestauranteApenasNomeModel  extends  RepresentationModel<RestauranteApenasNomeModel>{

	private Long id;
	private String nome;
}
