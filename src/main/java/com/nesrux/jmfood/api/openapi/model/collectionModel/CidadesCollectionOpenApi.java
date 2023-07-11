package com.nesrux.jmfood.api.openapi.model.collectionModel;

import java.util.List;

import org.springframework.hateoas.Links;

import com.nesrux.jmfood.api.model.dto.output.cidade.CidadeModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("cidadesModel")
public class CidadesCollectionOpenApi {
	
	@ApiModelProperty(position = 1)
	private CidadeEmbeddedModelOpenApi _embedded;
	
	@ApiModelProperty(position = 2)
	private Links _links;

	@ApiModel("cidadesEmbeddedModel")
	@Data
	public class CidadeEmbeddedModelOpenApi {
		private List<CidadeModel> cidades;
	}
}
