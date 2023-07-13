package com.nesrux.jmfood.api.v1.model.dto.input.restaurante;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteIdInput {
	@NotNull
	@ApiModelProperty(example = "1")
	private Long id;
}
