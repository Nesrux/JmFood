package com.nesrux.jmfood.api.model.dto.input.usuario;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrocarSenhaInput {

	@NotBlank
	@ApiModelProperty(example = "Senha123", required = true)
	private String senhaAtual;

	@NotBlank
	@ApiModelProperty(example = "JaumLindo@259", required = true)
	private String senhaNova;

}
