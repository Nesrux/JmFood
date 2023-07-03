package com.nesrux.jmfood.api.model.dto.output.formaPagamento;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel> {

	@ApiModelProperty(example = "1", position = 5)
	private Long id;

	@ApiModelProperty(example = "pix", position = 10)
	private String descricao;
}
