package com.nesrux.jmfood.api.v1.model.dto.input.restaurante;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.nesrux.jmfood.api.v1.model.dto.input.endereco.EnderecoInputDto;
import com.nesrux.jmfood.api.v1.model.dto.output.cozinha.CozinhaIDInputDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RestauranteInputDto {
	@ApiModelProperty(example = "EAI burguer", required = true, position = 5)
	@NotBlank
	private String nome;

	@PositiveOrZero
	@NotNull
	@ApiModelProperty(example = "15.50", required = true, position = 10)
	private BigDecimal taxaFrete;

	@Valid
	@NotNull
	private CozinhaIDInputDto cozinha;
	
	@Valid
	@NotNull
	private EnderecoInputDto endereco;

}
