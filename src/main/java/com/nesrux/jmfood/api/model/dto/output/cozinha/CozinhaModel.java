package com.nesrux.jmfood.api.model.dto.output.cozinha;

import com.fasterxml.jackson.annotation.JsonView;
import com.nesrux.jmfood.api.model.dto.view.RestauranteView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaModel {
	
	@JsonView(RestauranteView.resumo.class)
	private Long id;
	
	@JsonView(RestauranteView.resumo.class)
	private String nome;

}
