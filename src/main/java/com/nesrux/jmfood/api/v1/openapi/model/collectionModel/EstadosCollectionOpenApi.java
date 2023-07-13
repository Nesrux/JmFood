package com.nesrux.jmfood.api.v1.openapi.model.collectionModel;

import java.util.List;

import org.springframework.hateoas.Links;

import com.nesrux.jmfood.api.v1.model.dto.output.estado.EstadoModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("EstadosModel")
@Getter
@Setter
public class EstadosCollectionOpenApi {
	@ApiModelProperty(position = 1)
	private EstadoEmbeddedModelOpenApi _embedded;

	@ApiModelProperty(position = 5)
	private Links _links;

	@ApiModel("cidadesEmbeddedModel")
	@Data
	public class EstadoEmbeddedModelOpenApi {
		private List<EstadoModel> Estados;
	}
}
