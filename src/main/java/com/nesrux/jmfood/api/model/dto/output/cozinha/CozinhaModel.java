package com.nesrux.jmfood.api.model.dto.output.cozinha;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaModel {
	
	private Long id;
	
	@NotBlank
	private String nome;

}
