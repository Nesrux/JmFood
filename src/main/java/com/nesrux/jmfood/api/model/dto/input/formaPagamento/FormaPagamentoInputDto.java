package com.nesrux.jmfood.api.model.dto.input.formaPagamento;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInputDto {
	@NotBlank
	private String descricao;
}
