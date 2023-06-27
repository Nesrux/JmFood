package com.nesrux.jmfood.api.model.dto.output.cozinha;

import com.fasterxml.jackson.annotation.JsonView;
import com.nesrux.jmfood.api.model.dto.view.RestauranteView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaModel {
	
	@JsonView(RestauranteView.resumo.class)
	@ApiModelProperty(example = "1")
	private Long id;
	
	@JsonView(RestauranteView.resumo.class)
	@ApiModelProperty(example = "Tailandesa")
	private String nome;

}
