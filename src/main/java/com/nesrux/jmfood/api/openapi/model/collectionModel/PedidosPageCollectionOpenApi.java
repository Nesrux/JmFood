package com.nesrux.jmfood.api.openapi.model.collectionModel;

import java.util.List;

import org.springframework.hateoas.Links;

import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoResumoModel;
import com.nesrux.jmfood.api.openapi.model.PageModelOpenApi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("pedidosModel")
public class PedidosPageCollectionOpenApi {
	@ApiModelProperty(position = 1)
	private PedidosEmbeddedModelOpenApi _embedded;
	@ApiModelProperty(position = 10)
	private Links _links;
	@ApiModelProperty(position = 15)
	private PageModelOpenApi page;

	@Data
	@ApiModel("PedidosEmbeddedModel")
	public class PedidosEmbeddedModelOpenApi {
		@ApiModelProperty(position = 2)
		private List<PedidoResumoModel> pedidos;
	}

}
