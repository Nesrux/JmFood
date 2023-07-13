package com.nesrux.jmfood.api.v1.openapi.model.collectionModel;

import java.util.List;

import org.springframework.hateoas.Links;

import com.nesrux.jmfood.api.v1.model.dto.output.produto.ProdutoModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ProdutosModel")
public class ProdutosCollectionOpenApi {
	@ApiModelProperty(position = 1)
	private ProdutoEmbeddedModelOpenApi _embedded;

	@ApiModelProperty(position = 5)
	private Links _links;

	@ApiModel("produtoEmbeddedModel")
	@Data
	public class ProdutoEmbeddedModelOpenApi {
		private List<ProdutoModel> produtos;
	}

}
