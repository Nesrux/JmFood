package com.nesrux.jmfood.api.model.dto.output.restaurante;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResumoModel {
	@ApiModelProperty(example = "1", position = 5)
	private Long restauranteId;
	@ApiModelProperty(example = "thai gurmet", position = 10)
	private String nome;

}
