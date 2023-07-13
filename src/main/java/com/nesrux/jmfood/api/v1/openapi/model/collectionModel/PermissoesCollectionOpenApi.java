package com.nesrux.jmfood.api.v1.openapi.model.collectionModel;

import java.util.List;

import org.springframework.hateoas.Links;

import com.nesrux.jmfood.api.v1.model.dto.output.permissao.PermissaoModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("PermissoesModel")
public class PermissoesCollectionOpenApi {
	@ApiModelProperty(position = 1)
	private PermissoesEmbeddedModelOpenApi _embedded;

	@ApiModelProperty(position = 5)
	private Links _links;

	@ApiModel("permissoesEmbeddedModel")
	@Data
	public class PermissoesEmbeddedModelOpenApi {
		private List<PermissaoModel> permissoes;
	}

}
