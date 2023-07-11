package com.nesrux.jmfood.api.openapi.model.collectionModel;

import java.util.List;

import org.springframework.hateoas.Links;

import com.nesrux.jmfood.api.model.dto.output.cozinha.CozinhaModel;
import com.nesrux.jmfood.api.openapi.model.PageModelOpenApi;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhasModel")
@Getter
@Setter
public class CozinhasModelOpenApi {
	private CozinhasEmbeddedModelOpenApi _embedded;
	private Links _links;
	private PageModelOpenApi page;

	@Data
	@ApiModel("CozinhasEmbeddedModel")
	public class CozinhasEmbeddedModelOpenApi {
		private List<CozinhaModel> cozinhas;
	}
}
