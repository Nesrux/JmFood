package com.nesrux.jmfood.api.model.dto.input.endereco;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.nesrux.jmfood.api.model.dto.input.cidade.CidadeIDInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInputDto {
	@NotBlank
	private String cep;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String numero;
	
	private String complemento;
	
	@NotBlank
	private String bairro;
	
	@NotNull
	@Valid
	private CidadeIDInput cidade;
	
}
