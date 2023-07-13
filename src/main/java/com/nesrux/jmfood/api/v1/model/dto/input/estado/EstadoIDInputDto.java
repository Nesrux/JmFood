package com.nesrux.jmfood.api.v1.model.dto.input.estado;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIDInputDto {

	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long id;
}
