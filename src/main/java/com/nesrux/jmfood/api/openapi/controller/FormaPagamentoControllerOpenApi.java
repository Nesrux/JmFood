package com.nesrux.jmfood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

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
	ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request);

	@ApiOperation("Busca apenas 1 forma de pagamento")
	@ApiResponses({
		@ApiResponse(code=400, message = "Id da Forma de pagamento é inválido"),
		@ApiResponse(code = 404, message = "Forma de pagamento com esse id não foi encontrada")
	})
	ResponseEntity<FormaPagamentoModel> buscar(@ApiParam(value = "Id de uma Forma de pagamento", example = "1") Long formaPagamentoID,
			ServletWebRequest request);

	@ApiOperation("Salva uma forma de pagamento")
	@ApiResponses({
		@ApiResponse(code =  201, message="Forma de pagamento cadastrada com sucesso"),
		@ApiResponse(code = 400, message = "A Forma de pagamento não foi cadastrada")
	})
	FormaPagamentoModel adicionar(@ApiParam(name = "corpo", value ="Representação de uma forma de pagamento") FormaPagamentoInputDto inputDto);

	@ApiOperation("Atualiza uma forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 400, message = "A Forma de pagamento não foi atualizada"),
		@ApiResponse(code = 404, message = "A Forma de pagamento com Id passado não foi encontrada"),
		@ApiResponse(code =200, message = "A Forma de pagamento foi atualizada com suecesso")
	})
	FormaPagamentoModel atualizar(@ApiParam(name = "corpo", value ="Representação de uma forma de pagamento") FormaPagamentoInputDto inputDto,
		@ApiParam(value = "Id de uma forma de pagamento", example = "1") Long formaPagamentoId);

	@ApiOperation("Exclui uma forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 409, message = "Não foi possivel apagar a forma de pagamento pois ela esta sendo usada por outras entidades"),
		@ApiResponse(code = 404, message = "Não foi possivel apagar a forma de pagamento pois ela não foi encontrada"),
		@ApiResponse(code = 400, message = "Código na forma de pagamento é inválido"),
		@ApiResponse(code = 200, message = "forma de pagamento apagada com sucesso")
	})
	void excluir(@ApiParam(value = "Id de uma forma de pagamento", example = "1") Long formaPagamentoID);

}