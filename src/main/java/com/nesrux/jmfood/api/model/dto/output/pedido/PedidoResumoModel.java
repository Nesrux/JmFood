package com.nesrux.jmfood.api.model.dto.output.pedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.nesrux.jmfood.api.model.dto.output.restaurante.RestauranteResumoModel;
import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoModel {
	private Long id;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String statusPedido;
	private OffsetDateTime dataCricao;
	private RestauranteResumoModel restaurante;
	private UsuarioModel cliente;
}
