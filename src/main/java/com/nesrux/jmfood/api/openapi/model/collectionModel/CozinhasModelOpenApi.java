package com.nesrux.jmfood.api.openapi.model.collectionModel;

import java.util.List;

import org.springframework.hateoas.Links;

import com.nesrux.jmfood.api.model.dto.output.cozinha.CozinhaModel;
import com.nesrux.jmfood.api.openapi.model.PageModelOpenApi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhasModel")
@Getter
@Setter
public class CozinhasModelOpenApi {
	@ApiModelProperty(position = 1)
	private CozinhasEmbeddedModelOpenApi _embedded;
	@ApiModelProperty(position = 10)
	private Links _links;
	@ApiModelProperty(position = 15)
	private PageModelOpenApi page;

	@Data
	@ApiModel("CozinhasEmbeddedModel")
	public class CozinhasEmbeddedModelOpenApi {
		@ApiModelProperty(position = 2)
		private List<CozinhaModel> cozinhas;
	}
}
