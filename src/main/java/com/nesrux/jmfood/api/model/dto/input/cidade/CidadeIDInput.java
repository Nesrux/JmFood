package com.nesrux.jmfood.api.model.dto.input.cidade;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class CidadeIDInput {
	@NotNull
	private Long id;
}
