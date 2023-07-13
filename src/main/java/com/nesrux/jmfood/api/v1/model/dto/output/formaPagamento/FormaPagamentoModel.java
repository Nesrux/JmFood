package com.nesrux.jmfood.api.v1.model.dto.output.formaPagamento;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "formas-pagamento")
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel> {

	@ApiModelProperty(example = "1", position = 5)
	private Long id;

	@ApiModelProperty(example = "pix", position = 10)
	private String descricao;
}
