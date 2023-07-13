package com.nesrux.jmfood.api.v1.model.dto.output.estado;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "Estados")
public class EstadoModel extends RepresentationModel<EstadoModel> {

	@ApiModelProperty(example = "1", position = 1)
	private Long id;

	@ApiModelProperty(example = "São paulo", position = 1)
	private String nome;

}
