package com.nesrux.jmfood.api.model.dto.input.endereco;

import com.nesrux.jmfood.api.model.dto.input.cidade.CidadeIDInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInputDto {
	private String cep;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private CidadeIDInput cidade;
}
