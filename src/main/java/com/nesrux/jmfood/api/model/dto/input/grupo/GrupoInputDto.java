package com.nesrux.jmfood.api.model.dto.input.grupo;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInputDto {
	@NotBlank
	private String nome;
}
