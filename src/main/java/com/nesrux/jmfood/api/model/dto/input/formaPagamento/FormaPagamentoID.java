package com.nesrux.jmfood.api.model.dto.input.formaPagamento;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoID {
	@NotNull
	private Long id;
}
