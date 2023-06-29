package com.nesrux.jmfood.api.model.dto.input.estado;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInputDto {
	@NotBlank
	@ApiModelProperty(example = "Minas gerais",  position = 5)
	private String nome;
	
}
