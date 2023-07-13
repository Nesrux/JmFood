package com.nesrux.jmfood.api.v1.model.dto.input.formaPagamento;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoID {
	@NotNull
	@ApiModelProperty(example = "1")
	private Long id;
}
