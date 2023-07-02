package com.nesrux.jmfood.api.openapi.controller.cidades;

import org.springframework.hateoas.CollectionModel;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.model.dto.input.cidade.CidadeInputDto;
import com.nesrux.jmfood.api.model.dto.output.cidade.CidadeModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation("Listagem de cidades")
	public CollectionModel<CidadeModel> listar();

	@ApiOperation("Busca de cidades")
	@ApiResponses({ 
			@ApiResponse(code = 400, message = "ID da cidade é inválido", response = ErroApi.class),
			@ApiResponse(code = 404, message = "Cidade não encontrada", response = ErroApi.class), })
	public CidadeModel buscar(@ApiParam(value = "Id de uma Cidade", example = "1", required = true) Long cidadeId);

	@ApiOperation("Cadastro de cidades")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada"),
		@ApiResponse(code = 400, message = "Representação de cidade esta invalida", response = ErroApi.class),

	})
	public CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma cidade", required = true) CidadeInputDto cidadeInputDto);

	@ApiOperation("Atualização de cidades")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade Atualizada com sucesso"),
		@ApiResponse(code = 404, message = "cidade não encontrado", response = ErroApi.class),
		@ApiResponse(code = 400, message = "Id de cidade ou representação de cidade estão invalidos", response = ErroApi.class),

	})
	public CidadeModel atualizar(@ApiParam(value = "Id de uma Cidade", example = "1", required = true) Long cidadeId,
			@ApiParam(name = "corpo", value = "Representação de uma cidade", required = true) CidadeInputDto cidadeInputDto);

	@ApiOperation("Exclusão de cidades")
	@ApiResponses({ 
		@ApiResponse(code = 400, message = "ID da cidade é inválido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = ErroApi.class), 
		@ApiResponse(code = 204, message = "Cidade removida com sucesso")
	})

	public void excluir(@ApiParam(value = "Id de uma Cidade", example = "1", required = true) Long cidadeId);

}
