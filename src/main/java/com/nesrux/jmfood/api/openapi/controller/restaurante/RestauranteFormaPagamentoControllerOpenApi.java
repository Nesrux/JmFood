package com.nesrux.jmfood.api.openapi.controller.restaurante;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.model.dto.output.formaPagamento.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {
	
	@ApiOperation("Lista todas as formas de pagamento aceitas pelo restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Codigo do Restaurante ou da forma de pagamento inválido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Codigo do Restaurante ou da forma de pagamento não existe", response = ErroApi.class),
		@ApiResponse(code = 200, message = "Listagem feita com sucesso")
	})
	public CollectionModel<FormaPagamentoModel> listar(@ApiParam(value = "Id de um restaurante", example = "1", required = true)
			Long restauranteId);

	@ApiOperation("Desassocia uma forma de pagamento de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Codigo do Restaurante ou da forma de pagamento inválido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Codigo do Restaurante ou da forma de pagamento não existe", response = ErroApi.class),
		@ApiResponse(code = 204, message = "Dessasiciação feita com sucesso")
	})
	public ResponseEntity<Void> desassociarFormaPagamento(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(value = "Id de uma Forma de pagamento", example = "1", required = true)	Long formaPagamentoId);

	@ApiOperation("Associa uma forma de pagamento a um restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Codigo do Restaurante ou da forma de pagamento inválido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Codigo do Restaurante ou da forma de pagamento não existe", response = ErroApi.class),
		@ApiResponse(code = 204, message = "Associação feita com sucesso")
	})
	public ResponseEntity<Void> associarFormaPagamento(@ApiParam(value = "Id de um restaurante", example = "1", required = true)Long restauranteId,
			@ApiParam(value = "Id de uma Forma de pagamento", example = "1", required = true) Long formaPagamentoId);

}
