package com.nesrux.jmfood.api.model.dto.output.endereco;

import com.nesrux.jmfood.domain.model.endereco.Cidade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {
	private String cep;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private Cidade cidade;

}
