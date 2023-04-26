package com.nesrux.jmfood.domain.model.endereco;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Endereco {
	/*
	 * A anotação @Embeddable funciona juntamente com a anotação @Embedded, essa
	 * anotação "Fala" que essa classe pode ser "Consumida" e não é uma entidade
	 * separada, mas sim uma entidade que é parte de outra entidade
	 */

	@Column(name = "endereco_cep")
	private String cep;

	@Column(name = "endereco_logradouro")
	private String logradouro;

	@Column(name = "endereco_numero")
	private String numero;

	@Column(name = "endereco_complemento")
	private String complemento;

	@Column(name = "endereco_bairro")
	private String bairro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_cidade_id")
	private Cidade cidade;

}
