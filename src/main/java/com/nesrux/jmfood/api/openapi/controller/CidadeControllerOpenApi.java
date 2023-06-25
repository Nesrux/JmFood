package com.nesrux.jmfood.api.openapi.controller;

import java.util.List;

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
	public List<CidadeModel> listar();

	@ApiOperation("Busca de cidades")
	@ApiResponses({ 
			@ApiResponse(code = 400, message = "ID da cidade é inválido", response = ErroApi.class),
			@ApiResponse(code = 404, message = "Cidade não encontrada", response = ErroApi.class), })
	public CidadeModel buscar(@ApiParam(value = "Id de uma Cidade", example = "1") Long cidadeId);

	@ApiOperation("Cadastro de cidades")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Grupo cadastrado"),
	})
	public CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma cidade") CidadeInputDto cidadeInputDto);

	@ApiOperation("Atualização de cidades")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Grupo atualizado"),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = ErroApi.class)
	})
	public CidadeModel atualizar(@ApiParam(value = "Id de uma Cidade", example = "1") Long cidadeId,
			@ApiParam(name = "copo", value = "Representação de uma cidade") CidadeInputDto cidadeInputDto);

	@ApiOperation("Exclusão de cidades")
	public void excluir(@ApiParam(value = "Id de uma Cidade", example = "1") Long cidadeId);

}
