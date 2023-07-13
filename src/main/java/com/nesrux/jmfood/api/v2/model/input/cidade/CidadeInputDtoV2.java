package com.nesrux.jmfood.api.v2.model.input.cidade;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.nesrux.jmfood.api.v1.model.dto.input.estado.EstadoIDInputDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CidadeInput")
public class CidadeInputDtoV2 {
	
	@ApiModelProperty(example = "Sorocaba", required = true)
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EstadoIDInputDto estado;

}
