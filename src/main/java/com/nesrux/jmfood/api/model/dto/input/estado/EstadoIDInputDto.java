package com.nesrux.jmfood.api.model.dto.input.estado;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIDInputDto {

	@ApiModelProperty(example = "1")
	@NotNull
	private Long id;
}
