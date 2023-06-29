package com.nesrux.jmfood.api.model.dto.output.formaPagamento;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoModel {
	
	@ApiModelProperty(example = "1", required = true, position = 5)
	private Long id;
	
	@ApiModelProperty(example = "pix", required = true, position = 10)
	private String descricao;
}
