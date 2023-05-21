package com.nesrux.jmfood.api.model.dto.input.itens;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {
	
	private Long pedidoId;
	
	private Integer quantidade;
	
	private String observacao;

}
