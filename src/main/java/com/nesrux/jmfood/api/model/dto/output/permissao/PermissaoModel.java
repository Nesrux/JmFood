package com.nesrux.jmfood.api.model.dto.output.permissao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoModel {

	@ApiModelProperty(example = "1", position = 5)
	private Long id;

	@ApiModelProperty(example = "Administrativo", position = 10)
	private String nome;

	@ApiModelProperty(example = "acesso total ao sistema", position = 15)
	private String descricao;
}
