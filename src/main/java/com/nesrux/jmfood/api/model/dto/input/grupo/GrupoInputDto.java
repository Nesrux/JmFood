package com.nesrux.jmfood.api.model.dto.input.grupo;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInputDto {
	@NotBlank
	@ApiModelProperty(example = "Gerencia", required = true, position = 5)
	private String nome;
}
