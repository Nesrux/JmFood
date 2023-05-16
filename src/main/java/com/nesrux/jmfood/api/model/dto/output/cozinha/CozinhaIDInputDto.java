package com.nesrux.jmfood.api.model.dto.output.cozinha;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIDInputDto {
	@NotNull
	private Long id;
}
