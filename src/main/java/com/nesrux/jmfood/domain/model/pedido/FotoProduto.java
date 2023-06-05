package com.nesrux.jmfood.domain.model.pedido;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FotoProduto {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "produto_id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Produto produto;

	@Column(name = "nome_arquivo")
	private String nome;

	private String descricao;
	private String contentType;
	private Long tamanho;

}
