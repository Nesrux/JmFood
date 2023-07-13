package com.nesrux.jmfood.api.v1.model.dto.input.formaPagamento;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInputDto {
	@NotBlank
	@ApiModelProperty(example = "Boleto", required = true, position = 5)
	private String descricao;
}
