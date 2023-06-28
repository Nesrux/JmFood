package com.nesrux.jmfood.api.model.dto.input.produto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInputDto {
	@NotBlank
	@ApiModelProperty(example = "Frango caipira", required = true, position = 5)
	private String nome;

	@NotBlank
	@ApiModelProperty(example = "Frango caipira com molho agridoce", required = true, position = 10)
	private String descricao;

	@NotNull
	@PositiveOrZero
	@ApiModelProperty(example = "23", required = true, position = 15)
	private BigDecimal preco;

	@NotNull
	@ApiModelProperty(example = "true", position = 20)
	private boolean ativo;
}
