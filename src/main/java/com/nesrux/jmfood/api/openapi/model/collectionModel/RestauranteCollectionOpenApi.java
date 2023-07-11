package com.nesrux.jmfood.api.openapi.model.collectionModel;

import java.util.List;

import org.springframework.hateoas.Links;

import com.nesrux.jmfood.api.model.dto.output.restaurante.RestauranteResumoModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel("RestauranteModel")
public class RestauranteCollectionOpenApi {
	@ApiModelProperty(position = 1)
	private RestaurantesEmbeddedModelOpenApi _embedded;

	@ApiModelProperty(position = 5)
	private Links _links;

	@ApiModel("RestaurantesEmbeddedModel")
	@Data
	public class RestaurantesEmbeddedModelOpenApi {
		private List<RestauranteResumoModel> restaurantes;
	}
}
