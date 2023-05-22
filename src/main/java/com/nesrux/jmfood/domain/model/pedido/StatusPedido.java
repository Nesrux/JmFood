package com.nesrux.jmfood.domain.model.pedido;

public enum StatusPedido {
//TODO aprender a trabalhar melhor com enum
	CRIADO("Criado"),
	CONFIRMADO("Confirmado"),
	ENTREGE("Entregue"), 
	CANCELADO("Cancelado");

	private String descricao;

	StatusPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
