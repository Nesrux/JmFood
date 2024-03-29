package com.nesrux.jmfood.api.v1.model.dto.output.restaurante;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResumoModel extends RepresentationModel<RestauranteResumoModel> {
	@ApiModelProperty(example = "1", position = 5)
	private Long restauranteId;
	@ApiModelProperty(example = "thai gurmet", position = 10)
	private String nome;

}
