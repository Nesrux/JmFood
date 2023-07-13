package com.nesrux.jmfood.api.v1.openapi.model.collectionModel;

import java.util.List;

import org.springframework.hateoas.Links;

import com.nesrux.jmfood.api.v1.model.dto.output.formaPagamento.FormaPagamentoModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("formasPagamentoModel")
public class FormaPagamentoCollectionOpenApi {
	@ApiModelProperty(position = 1)
	private FormaPagamentoCollectionOpenApi _embedded;

	@ApiModelProperty(position = 5)
	private Links _links;

	@ApiModel("cidadesEmbeddedModel")
	@Data
	public class FormaPagamentoEmbeddedModelOpenApi {
		private List<FormaPagamentoModel> formaPagamento;
	}

}
