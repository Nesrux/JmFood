package com.nesrux.jmfood.api.model.dto.input.cidade;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.nesrux.jmfood.api.model.dto.input.estado.EstadoIDInputDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInputDto {
	
	@ApiModelProperty(example = "Sorocaba", required = true)
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EstadoIDInputDto estado;

}
