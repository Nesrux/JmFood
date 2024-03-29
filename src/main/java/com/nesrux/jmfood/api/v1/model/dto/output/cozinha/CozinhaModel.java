package com.nesrux.jmfood.api.v1.model.dto.output.cozinha;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "cozinhas")
public class CozinhaModel extends RepresentationModel<CozinhaModel> {

	// @JsonView(RestauranteView.resumo.class)
	@ApiModelProperty(example = "1")
	private Long id;

	// @JsonView(RestauranteView.resumo.class)
	@ApiModelProperty(example = "Tailandesa")
	private String nome;

}
