package com.nesrux.jmfood.api.v2.model.input.cozinha;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInputDtoV2 {
	
	@NotBlank
	@ApiModelProperty(example = "Brasileira", required = true, position = 5)
	private String nome;

}
