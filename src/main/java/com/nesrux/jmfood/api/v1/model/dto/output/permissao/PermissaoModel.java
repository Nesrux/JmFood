package com.nesrux.jmfood.api.v1.model.dto.output.permissao;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "permissoes")
public class PermissaoModel extends RepresentationModel<PermissaoModel> {

	@ApiModelProperty(example = "1", position = 5)
	private Long id;

	@ApiModelProperty(example = "Administrativo", position = 10)
	private String nome;

	@ApiModelProperty(example = "acesso total ao sistema", position = 15)
	private String descricao;
}
