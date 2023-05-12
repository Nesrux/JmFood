package com.nesrux.jmfood.api.model.dto.input.restaurante;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RestauranteInputDto {

	@NotBlank
	private String nome;

	@PositiveOrZero
	@NotNull
	private BigDecimal taxaFrete;

	@Valid
	@NotNull
	private CozinhaIdInputDto cozinha;

}
