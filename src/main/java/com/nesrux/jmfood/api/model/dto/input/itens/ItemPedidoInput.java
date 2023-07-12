package com.nesrux.jmfood.api.model.dto.input.itens;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {
	@ApiModelProperty(example = "1")
	private Long produtoId;
	@ApiModelProperty(example = "1")
	private Integer quantidade;
	@ApiModelProperty(example = "Sem queijo")
	private String observacao;

}
