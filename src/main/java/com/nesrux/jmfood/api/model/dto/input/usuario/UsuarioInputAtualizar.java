package com.nesrux.jmfood.api.model.dto.input.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UsuarioInputAtualizar {
	@NotBlank
	private String nome;
	
	@Email
	private String Email;
	
}
