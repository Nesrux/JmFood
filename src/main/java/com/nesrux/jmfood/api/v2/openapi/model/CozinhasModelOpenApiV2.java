package com.nesrux.jmfood.api.v2.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.nesrux.jmfood.api.v1.openapi.model.PageModelOpenApi;
import com.nesrux.jmfood.api.v2.model.output.cozinha.CozinhaModelV2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CozinhasModel")
public class CozinhasModelOpenApiV2 {

	@ApiModelProperty(position = 1)
	private CozinhasEmbeddedModelOpenApiV2 _embedded;
	@ApiModelProperty(position = 10)
	private Links _links;
	@ApiModelProperty(position = 15)
	private PageModelOpenApi page;

	@Data
	@ApiModel("CozinhasEmbeddedModel")
	public class CozinhasEmbeddedModelOpenApiV2 {
		@ApiModelProperty(position = 2)
		private List<CozinhaModelV2> cozinhas;
	}

}
