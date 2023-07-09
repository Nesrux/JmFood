package com.nesrux.jmfood.api.model.dto.output.grupo;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "grupos")
public class GrupoModel extends RepresentationModel<GrupoModel> {
	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	@ApiModelProperty(example = "Administrativo", position = 5)
	private String nome;
}
