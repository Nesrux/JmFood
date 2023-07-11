package com.nesrux.jmfood.api.openapi.model.collectionModel;

import java.util.List;

import org.springframework.hateoas.Links;

import com.nesrux.jmfood.api.model.dto.output.grupo.GrupoModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("GruposModel")
public class GrupoCollectionOpenApi {
	@ApiModelProperty(position = 1)
	private GruposEmbeddedModelOpenApi _embedded;

	@ApiModelProperty(position = 5)
	private Links _links;

	@ApiModel("gruposEmbeddedModel")
	@Data
	public class GruposEmbeddedModelOpenApi {
		private List<GrupoModel> grupos;
	}

}
