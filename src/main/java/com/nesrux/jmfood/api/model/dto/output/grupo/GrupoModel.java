package com.nesrux.jmfood.api.model.dto.output.grupo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoModel {
	@ApiModelProperty(example = "1", required = true, position = 5)
	private Long id;
	@ApiModelProperty(example = "Administrativo", required = true, position = 5)
	private String nome;
}
