package com.nesrux.jmfood.api.v2.model.output.cidade;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "Cidades") // Muda o nome da colecction em json
@ApiModel("cidadeModel")
public class CidadeModelV2 extends RepresentationModel<CidadeModelV2> {
	@ApiModelProperty(value = "ID da cidade", example = "1", position = 1)
	private Long id;

	@ApiModelProperty(example = "São paulo", position = 5)
	private String nome;
	// @ApiModelProperty(position = 10)
	// private EstadoModel estado;
}
