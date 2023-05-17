package com.nesrux.jmfood.api.model.dto.input.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {
	
	@NotBlank
	private String nome;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String senha;

}
