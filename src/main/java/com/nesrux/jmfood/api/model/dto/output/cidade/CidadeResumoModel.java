package com.nesrux.jmfood.api.model.dto.output.cidade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoModel {
	@ApiModelProperty(value = "Id de uma cidade", example = "1", position = 5)
	private Long id;
	@ApiModelProperty(value = "Nome de uma cidade", example = "Rio de janeiro", position = 15)
	private String nome;
	@ApiModelProperty(value = "Nome de um estado", example = "Rio de janeiro", position = 15)
	private String estado;
}
