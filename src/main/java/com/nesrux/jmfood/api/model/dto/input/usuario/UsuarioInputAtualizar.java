package com.nesrux.jmfood.api.model.dto.input.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputAtualizar {
	@NotBlank
	@ApiModelProperty(example = "Juliano", required = true)
	private String nome;

	@Email
	@ApiModelProperty(example = "Juliano@jmfood.com", required = true)
	private String Email;

}
