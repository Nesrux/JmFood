package com.nesrux.jmfood.api.openapi.model;

import java.math.BigDecimal;

import com.nesrux.jmfood.api.model.dto.output.cozinha.CozinhaModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RestauranteBasicoModel")
public class RestauranteBasicoOpenApi {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Thai gurmet")
	private String nome;
	
	@ApiModelProperty(example = "15.45")
	private BigDecimal taxaFrete;
	
	private CozinhaModel cozinha;
}
