package com.nesrux.jmfood.api.model.dto.input.cozinha;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInputDto {
	@NotBlank
	private String nome;

}
