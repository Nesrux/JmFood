package com.nesrux.jmfood.api.model.dto.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIdInputDTO {
	@NotNull
	private Long id;
}
