package com.nesrux.jmfood.api.model.dto.output.itemPedido;

import java.math.BigDecimal;

import com.nesrux.jmfood.api.model.dto.output.produto.ProdutoModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {
	
	private Long id;
	
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private ProdutoModel produto;
	
}
