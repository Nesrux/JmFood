package com.nesrux.jmfood.api.openapi.model.collectionModel;

import java.util.List;

import org.springframework.hateoas.Links;

import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCollectionOpenApi {
	@ApiModelProperty(position = 1)
	private UsuarioEmbeddedModelOpenApi _embedded;

	@ApiModelProperty(position = 5)
	private Links _links;

	@ApiModel("permissoesEmbeddedModel")
	@Data
	public class UsuarioEmbeddedModelOpenApi {
		private List<UsuarioModel> usuarios;
	}
}
