package com.nesrux.jmfood.api.openapi.controller;

import java.util.List;

import com.nesrux.jmfood.api.model.dto.input.restaurante.RestauranteInputDto;
import com.nesrux.jmfood.api.model.dto.output.restaurante.RestauranteModel;
import com.nesrux.jmfood.api.openapi.model.RestauranteBasicoOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

	@ApiOperation(value = "Listagem de restaurantes", response = RestauranteBasicoOpenApi.class)
	@ApiImplicitParams({
			@ApiImplicitParam(value = "nome da projeção de pedidos",
					allowableValues = "apenas-nome",
					name = "projeção",
					paramType = "query",
					type = "string") })
	public List<RestauranteModel> listarResumo();

	@ApiOperation(value = "Listagem de restaurantes", hidden = true)
	public List<RestauranteModel> listarNomes();
	
	@ApiOperation("Busca de um unico restaurante")
	public RestauranteModel buscar(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId);
	
	@ApiOperation("Cadastro de um restaurante")
	public RestauranteModel adicionar(@ApiParam(name = "corpo", value = "Representação de um restaurante", required = true)
		RestauranteInputDto restauranteInputDTO);
	
	@ApiOperation("Atualização de um restaurante")
	public RestauranteModel atualizar(@ApiParam(value = "Id de um restaurante", example = "1", required = true)Long restauranteId,
			@ApiParam(name = "corpo", value = "Representação de um restaurante", required = true) RestauranteInputDto restauranteInputDto);
	
	@ApiOperation("Ativação de restaurantes em massa")
	public void ativarRestaurantes(List<Long> restaurantes);
	
	@ApiOperation("desativação de restaurantes em massa")
	public void desativarRestaurantes(List<Long> restauranteids);
	
	@ApiOperation("Ativação de um unico restaurante")
	public void ativarRestaurante(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId);
	
	@ApiOperation("Desativação de um unico restaurante")
	public void desativarRestaurante(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId);
	
	@ApiOperation("Fechamento de um unico restaurante")
	public void fecharRestaurante(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId);
	
	@ApiOperation("Abertura de um unico restaurante")
	public void abrirRestaurante(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId);

}
