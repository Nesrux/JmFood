package com.nesrux.jmfood.api.v1.model.dto.input.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {
	
	@NotBlank
	@ApiModelProperty(required = true, example = "Gerundino lima")
	private String nome;

	@Email
	@NotBlank
	@ApiModelProperty(required = true, example = "GerundinolimaDasilvax23@gmail.com")
	private String email;

	@NotBlank
	@ApiModelProperty(required = true, example = "jmfoods@jmfoods")
	private String senha;

}
