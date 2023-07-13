package com.nesrux.jmfood.api.v1.model.dto.output.usuario;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "Usuarios")
public class UsuarioModel extends RepresentationModel<UsuarioModel>{
	
	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	@ApiModelProperty(example = "carlos", position = 10)
	private String nome;
	@ApiModelProperty(example = "carlos@jmgfood.com", position = 15)
	private String Email;

}
