package com.nesrux.jmfood.api.model.dto.output.cidade;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.nesrux.jmfood.api.model.dto.output.estado.EstadoModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "Cidades") //Muda o nome da colecction em json
public class CidadeModel extends RepresentationModel<CidadeModel> {
	@ApiModelProperty(value = "ID da cidade", example = "1")
	private Long id;

	@ApiModelProperty(example = "São paulo")
	private String nome;

	private EstadoModel estado;
}
