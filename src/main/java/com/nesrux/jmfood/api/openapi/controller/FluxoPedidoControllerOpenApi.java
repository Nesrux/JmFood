package com.nesrux.jmfood.api.openapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

	@ApiOperation("Confirmação de um pedido")
	public void confirmarPedido(
			@ApiParam(value = "codigo de um pedido", example = "d178b637-a785-4768-a3cb-aa1ce5a8cdab", required = true) String codigoPedido);

	@ApiOperation("entrega de um pedido")
	public void entregarPedido(
			@ApiParam(value = "codigo de um pedido", example = "d178b637-a785-4768-a3cb-aa1ce5a8cdab", required = true) String codigoPedido);

	@ApiOperation("Cancelamento de um pedido")
	public void cancelarPedido(
			@ApiParam(value = "codigo de um pedido", example = "d178b637-a785-4768-a3cb-aa1ce5a8cdab", required = true) String codigoPedido);

}
