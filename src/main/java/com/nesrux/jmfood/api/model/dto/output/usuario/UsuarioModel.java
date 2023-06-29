package com.nesrux.jmfood.api.model.dto.output.usuario;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {
	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	@ApiModelProperty(example = "carlos", position = 10)
	private String nome;
	@ApiModelProperty(example = "carlos@jmgfood.com", position = 15)
	private String Email;

}
