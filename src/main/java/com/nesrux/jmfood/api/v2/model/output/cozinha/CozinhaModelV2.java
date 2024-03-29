package com.nesrux.jmfood.api.v2.model.output.cozinha;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "cozinhas")
@ApiModel("CozinhaModel")
public class CozinhaModelV2 extends RepresentationModel<CozinhaModelV2> {

	// @JsonView(RestauranteView.resumo.class)
	@ApiModelProperty(example = "1")
	private Long cozinhaId;

	// @JsonView(RestauranteView.resumo.class)
	@ApiModelProperty(example = "Tailandesa")
	private String cozinhaNome;

}
