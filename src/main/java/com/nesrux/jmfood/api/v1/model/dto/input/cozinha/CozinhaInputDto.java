package com.nesrux.jmfood.api.v1.model.dto.input.cozinha;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInputDto {
	
	@NotBlank
	@ApiModelProperty(example = "Brasileira", required = true, position = 5)
	private String nome;

}
