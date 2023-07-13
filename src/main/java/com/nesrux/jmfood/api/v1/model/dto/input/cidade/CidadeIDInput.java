package com.nesrux.jmfood.api.v1.model.dto.input.cidade;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class CidadeIDInput {
	@NotNull
	@ApiModelProperty(example = "1", required = true, position = 5)
	private Long id;
}
