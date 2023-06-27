package com.nesrux.jmfood.api.openapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

	@ApiOperation("Confirmação de um pedido")
	public void confirmarPedido(String codigoPedido);

	@ApiOperation("entrega de um pedido")
	public void entregarPedido(String codigoPedido);

	@ApiOperation("Cancelamento de um pedido")
	public void cancelarPedido(String codigoPedido);

}
