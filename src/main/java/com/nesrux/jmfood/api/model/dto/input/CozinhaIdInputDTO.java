package com.nesrux.jmfood.api.model.dto.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/*
 * Classe para utilizar na entrada de dados de de uma cozinha no controller de
 * Restaurante
 */
public class CozinhaIdInputDTO {
	@NotNull
	private Long id;
}
