package com.nesrux.jmfood.api.model.dto.output.cozinha;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonView;
import com.nesrux.jmfood.api.model.dto.view.RestauranteView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "cozinhas")
public class CozinhaModel extends RepresentationModel<CozinhaModel> {
	
	@JsonView(RestauranteView.resumo.class)
	@ApiModelProperty(example = "1")
	private Long id;
	
	@JsonView(RestauranteView.resumo.class)
	@ApiModelProperty(example = "Tailandesa")
	private String nome;

}
