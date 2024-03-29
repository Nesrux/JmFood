package com.nesrux.jmfood.api.v1.model.dto.output.cidade;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.nesrux.jmfood.api.v1.model.dto.output.estado.EstadoModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "Cidades") // Muda o nome da colecction em json
public class CidadeModel extends RepresentationModel<CidadeModel> {
	@ApiModelProperty(value = "ID da cidade", example = "1", position = 1)
	private Long id;

	@ApiModelProperty(example = "São paulo", position = 5)
	private String nome;
	@ApiModelProperty(position = 10)
	private EstadoModel estado;
}
