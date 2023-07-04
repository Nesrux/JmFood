package com.nesrux.jmfood.api.openapi.controller.pedido;

import org.springframework.http.ResponseEntity;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

	@ApiOperation("Confirmação de um pedido")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id de pedido inválido", response = ErroApi.class),
		@ApiResponse(code =  404, message =  "Não foi possivel confirmar pedido, pois o pedido com esse código nao existe", response = ErroApi.class),
		@ApiResponse(code = 204, message = "Confirmação de pedido feito com sucesso")
	})
	public ResponseEntity<Void> confirmarPedido(
			@ApiParam(value = "codigo de um pedido", example = "d178b637-a785-4768-a3cb-aa1ce5a8cdab", required = true) String codigoPedido);

	@ApiOperation("entrega de um pedido")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id de pedido inválido", response = ErroApi.class),
		@ApiResponse(code =  404, message =  "Não foi possivel entregar pedido, pois o pedido com esse código nao existe", response = ErroApi.class),
		@ApiResponse(code = 204, message = "Entrega de pedido confirmado com sucesso")
	})
	public ResponseEntity<Void> entregarPedido(
			@ApiParam(value = "codigo de um pedido", example = "d178b637-a785-4768-a3cb-aa1ce5a8cdab", required = true) String codigoPedido);

	@ApiOperation("Cancelamento de um pedido")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id de pedido inválido", response = ErroApi.class),
		@ApiResponse(code =  404, message =  "Não foi possivel cancelar pedido, pois o pedido com esse código nao existe", response = ErroApi.class),
		@ApiResponse(code = 204, message = "Cancelamento de pedido feito com sucesso")
	})
	public ResponseEntity<Void> cancelarPedido(
			@ApiParam(value = "codigo de um pedido", example = "d178b637-a785-4768-a3cb-aa1ce5a8cdab", required = true) String codigoPedido);

}
