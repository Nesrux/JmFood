package com.nesrux.jmfood.api.v2.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.v2.model.input.cozinha.CozinhaInputDtoV2;
import com.nesrux.jmfood.api.v2.model.output.cozinha.CozinhaModelV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApiV2 {
	
	@ApiOperation("lisatagem de Cozinhas")
	PagedModel<CozinhaModelV2> listar(Pageable page);

	@ApiOperation("Busca de cozinhas")
	@ApiResponses({ 
			@ApiResponse(code = 400, message = "Id da cozinha é inválido", response = ErroApi.class),
			@ApiResponse(code = 404, message = "Id de cozinha não Existe", response = ErroApi.class),
			@ApiResponse(code = 200, message = "Cozinha encontrada com sucesso")
	})
	CozinhaModelV2 buscar(@ApiParam(value = "Id de uma cozinha", example = "1", required = true) Long cozinhaId);

	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Cozinha cadastrada com sucesso"),
		@ApiResponse(code = 400, message = "Cozinha não foi cadastrada", response = ErroApi.class)
	})
	@ApiOperation("Cadastro de cozinhas")
	CozinhaModelV2 adicionar(@ApiParam(name = "corpo", value = "Representação de uma cidade", required = true) CozinhaInputDtoV2 cozinhaInputDto);

	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Cozinha atualizada com sucesso"),
		@ApiResponse(code = 400, message = "Cozinha não foi atualizada", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Id de cozinha não existe", response = ErroApi.class)
		})
	@ApiOperation("Atualização de cozinhas")
	CozinhaModelV2 atualizar(@ApiParam(value = "Id de uma cozinha", example = "1", required = true) Long cozinhaId,
			@ApiParam(name = "corpo", value = "Representação de uma cidade", required = true) CozinhaInputDtoV2 cozinhaInputDto);
	
	@ApiOperation("Exclusão de cozinhas")
	@ApiResponses({ 
		@ApiResponse(code = 203, message = "Cozinha apagada com sucesso"),
		@ApiResponse(code = 404, message = "Cozinha não foi encontrada", response = ErroApi.class),
		@ApiResponse(code = 400, message = "codigo da cozinha inválido", response = ErroApi.class),
		@ApiResponse(code = 409, message = "não foi possivel apagar a cozinha, pois esta dando erro de conflito", response = ErroApi.class)
	})
	void deletar(@ApiParam(value = "Id de uma cozinha", example = "1", required = true) Long cozinhaId);

}