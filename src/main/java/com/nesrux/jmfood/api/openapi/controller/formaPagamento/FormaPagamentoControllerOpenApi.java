package com.nesrux.jmfood.api.openapi.controller.formaPagamento;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.model.dto.input.formaPagamento.FormaPagamentoInputDto;
import com.nesrux.jmfood.api.model.dto.output.formaPagamento.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Formas de pagamento")
public interface FormaPagamentoControllerOpenApi {

	@ApiOperation("Lista as formas de pagamento")
	ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request);

	@ApiOperation("Busca apenas 1 forma de pagamento")
	@ApiResponses({ @ApiResponse(code = 400, message = "Id da Forma de pagamento é inválido", response = ErroApi.class),
			@ApiResponse(code = 404, message = "Forma de pagamento com esse id não foi encontrada", response = ErroApi.class),
			@ApiResponse(code = 200, message = "Estado encontrado com sucesso") })
	ResponseEntity<FormaPagamentoModel> buscar(
			@ApiParam(value = "Id de uma Forma de pagamento", example = "1", required = true) Long formaPagamentoID,
			ServletWebRequest request);

	@ApiOperation("Cadastra uma forma de pagamento")
	@ApiResponses({ @ApiResponse(code = 201, message = "Forma de pagamento cadastrada com sucesso"),
			@ApiResponse(code = 400, message = "A representação de forma de pagamento esta inválida", response = ErroApi.class) })
	FormaPagamentoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma forma de pagamento", required = true) FormaPagamentoInputDto inputDto);

	@ApiOperation("Atualiza uma forma de pagamento")
	@ApiResponses({
			@ApiResponse(code = 400, message = "Id de forma-Pagamento ou reoresentação de forma pagamento estão inválidos", response = ErroApi.class),
			@ApiResponse(code = 404, message = "A Forma de pagamento com Id passado não foi encontrada", response = ErroApi.class),
			@ApiResponse(code = 200, message = "A Forma de pagamento foi atualizada com suecesso") })
	FormaPagamentoModel atualizar(
			@ApiParam(name = "corpo", value = "Representação de uma forma de pagamento", required = true) FormaPagamentoInputDto inputDto,
			@ApiParam(value = "Id de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId);

	@ApiOperation("Exclui uma forma de pagamento")
	@ApiResponses({
			@ApiResponse(code = 409, message = "Não foi possivel apagar a forma de pagamento pois ela esta sendo usada por outras entidades", response = ErroApi.class),
			@ApiResponse(code = 404, message = "Não foi possivel apagar a forma de pagamento pois ela não foi encontrada", response = ErroApi.class),
			@ApiResponse(code = 400, message = "Código na forma de pagamento é inválido", response = ErroApi.class),
			@ApiResponse(code = 200, message = "forma de pagamento apagada com sucesso") })
	void excluir(
			@ApiParam(value = "Id de uma forma de pagamento", example = "1", required = true) Long formaPagamentoID);

}