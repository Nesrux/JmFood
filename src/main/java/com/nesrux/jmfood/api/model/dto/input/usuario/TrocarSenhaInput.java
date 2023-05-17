package com.nesrux.jmfood.api.model.dto.input.usuario;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrocarSenhaInput {
	
	@NotBlank
	private String senhaAtual;
	
	@NotBlank
	private String senhaNova;

}
