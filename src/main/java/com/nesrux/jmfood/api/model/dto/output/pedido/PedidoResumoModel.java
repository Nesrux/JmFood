package com.nesrux.jmfood.api.model.dto.output.pedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.nesrux.jmfood.api.model.dto.output.restaurante.RestauranteResumoModel;
import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoModel {
	@ApiModelProperty(example = "d80c60f2-1505-4f15-b5bc-3cc6e398ee6d", position = 5)
	private String codigo;
	@ApiModelProperty(example = "152.50", position = 10)
	private BigDecimal subtotal;
	@ApiModelProperty(example = "10", position = 15)
	private BigDecimal taxaFrete;
	@ApiModelProperty(example = "162.50", position = 20)
	private BigDecimal valorTotal;
	@ApiModelProperty(example = "ENTREGUE", position = 5)
	private String statusPedido;
	@ApiModelProperty(example = "2023/06/29T13:30Z", position = 5)
	private OffsetDateTime dataCriacao;

	private RestauranteResumoModel restaurante;

	private UsuarioModel cliente;
}
