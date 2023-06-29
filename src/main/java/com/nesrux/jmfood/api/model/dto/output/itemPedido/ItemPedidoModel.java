package com.nesrux.jmfood.api.model.dto.output.itemPedido;

import java.math.BigDecimal;

import com.nesrux.jmfood.api.model.dto.output.produto.ProdutoModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {
	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	
	@ApiModelProperty(example = "15", position = 10)
	private Integer quantidade;
	
	@ApiModelProperty(example = "10", position = 15)
	private BigDecimal precoUnitario;

	@ApiModelProperty(example = "150", position = 20)
	private BigDecimal precoTotal;

	private ProdutoModel produto;

}
