package com.nesrux.jmfood.api.model.dto.input.itens;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {
	
	private Long produtoId;
	
	private Integer quantidade;
	
	private String observacao;

}
