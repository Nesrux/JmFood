package com.nesrux.jmfood.api.model.dto.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RestauranteInputDTO {

	@NotBlank
	private String nome;

	@PositiveOrZero
	@NotNull
	private BigDecimal taxaFrete;

	@Valid
	@NotNull
	private CozinhaIdInputDTO cozinha;

}
