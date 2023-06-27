package com.nesrux.jmfood.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.model.dto.input.cozinha.CozinhaInputDto;
import com.nesrux.jmfood.api.model.dto.output.cozinha.CozinhaModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

	@ApiOperation("lisatagem de Cozinhas")
	public Page<CozinhaModel> listar(Pageable page);

	@ApiOperation("Busca de cozinhas")
	@ApiResponses({ @ApiResponse(code = 400, message = "Id da cozinha é inválido"),
			@ApiResponse(code = 404, message = "Cozinha não encontrada") })
	public CozinhaModel buscar(@ApiParam(value = "Id de uma cozinha", example = "1") Long cozinhaId);

	@ApiResponses({ 
			@ApiResponse(code = 201, message = "Cozinha cadastrada com sucesso"),
			@ApiResponse(code = 400, message = "Cozinha não foi cadastrada", response = ErroApi.class)
			})
	@ApiOperation("Cadastro de cozinhas")
	public CozinhaModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma cidade") CozinhaInputDto cozinhaInputDto);

	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Cozinha atualizada com sucesso"),
		@ApiResponse(code = 400, message = "Cozinha não foi atualizada", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Cozinha não foi encontrada")
		})
	@ApiOperation("Atualização de cozinhas")
	public CozinhaModel atualizar(@ApiParam(value = "Id de uma cozinha", example = "1") Long cozinhaId,
			@ApiParam(name = "corpo", value = "Representação de uma cidade")CozinhaInputDto cozinhaInputDto);

	@ApiOperation("Exclusão de cozinhas")
	@ApiResponses({ 
		@ApiResponse(code = 203, message = "Cozinha apagada com sucesso"),
		@ApiResponse(code = 404, message = "Cozinha não foi encontrada", response = ErroApi.class),
		@ApiResponse(code = 400, message = "codigo da cozinha inválido"),
		@ApiResponse(code = 409, message = "não foi possivel apagar a cozinha, pois esta dando erro de conflito")
	})
	public void deletar(@ApiParam(value = "Id de uma cozinha", example = "1") Long cozinhaId);

}