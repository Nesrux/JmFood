package com.nesrux.jmfood.domain.model.pedido;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
//TODO aprender a trabalhar melhor com enum
	CRIADO("Criado"),
	CONFIRMADO("Confirmado", CRIADO),
	ENTREGUE("Entregue", CONFIRMADO), 
	CANCELADO("Cancelado",CRIADO, CONFIRMADO);

	private String descricao;
	private List<StatusPedido> statusAnteriores;

	StatusPedido(String descricao, StatusPedido...pedidos) {
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList(pedidos);
	}

	public String getDescricao() {
		return this.descricao;
	}
	public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
		return !novoStatus.statusAnteriores.contains(this);
	}
	public boolean podeAlterarPara(StatusPedido novoStatus) {
		return !naoPodeAlterarPara(novoStatus);
	}

}
