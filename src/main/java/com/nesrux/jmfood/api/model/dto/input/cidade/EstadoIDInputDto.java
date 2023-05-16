package com.nesrux.jmfood.api.model.dto.input.cidade;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIDInputDto {
	@NotNull
	private Long id;
}
