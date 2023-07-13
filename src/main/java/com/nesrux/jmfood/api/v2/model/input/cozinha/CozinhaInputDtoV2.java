package com.nesrux.jmfood.api.v2.model.input.cozinha;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CozinhaInput")
public class CozinhaInputDtoV2 {
	
	@NotBlank
	@ApiModelProperty(example = "Brasileira", required = true, position = 5)
	private String nome;

}
