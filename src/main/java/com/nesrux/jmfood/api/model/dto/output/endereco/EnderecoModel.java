package com.nesrux.jmfood.api.model.dto.output.endereco;

import com.nesrux.jmfood.api.model.dto.output.cidade.CidadeResumoModel;

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

	private CidadeResumoModel cidade;

}
