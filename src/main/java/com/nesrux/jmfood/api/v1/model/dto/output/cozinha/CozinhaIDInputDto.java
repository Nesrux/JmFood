package com.nesrux.jmfood.api.v1.model.dto.output.cozinha;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIDInputDto {
	@NotNull
	@ApiModelProperty(example = "1", required = true, position = 5)
	private Long id;
}
