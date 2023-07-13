package com.nesrux.jmfood.api.v1.openapi.controller.estados;

import org.springframework.hateoas.CollectionModel;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.v1.model.dto.input.estado.EstadoInputDto;
import com.nesrux.jmfood.api.v1.model.dto.output.estado.EstadoModel;
import com.nesrux.jmfood.domain.model.endereco.Estado;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenapi {
	@ApiOperation("Listagem de cidades")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Listagem do estado feito com sucesso" )
	})
	CollectionModel<EstadoModel> listar();

	@ApiOperation("Busca de um unico estado")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Não foi possivel encontrar o Estado pois o ID esta inválido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Não foi possivel encontrar o Estado, pois a cidade com este id não existe", response = ErroApi.class),
	})
	EstadoModel buscar(@ApiParam(value = "Id de um Estado", example = "1", required = true)  Long estadoId);

	@ApiOperation("Cadastro de um estado")
	@ApiResponses({
		@ApiResponse(code =  201, message = "Estado salvo com sucesso"),
		@ApiResponse(code =  400, message = "Não foi possivel salvar o estado pois a representação esta inválida")
	})
	EstadoModel adicionar(@ApiParam(name = "corpo", value = "Representação de um Estado", required = true) EstadoInputDto estadoDto);

	@ApiOperation("Atualização de uma cidade")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Não foi possivel atualizar o Estado pois o ID esta inválido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Não foi possivel atualizar o Estado, pois ela não existe", response = ErroApi.class),
		@ApiResponse(code =  200, message = "Estado atualizado com sucesso")
	})
	Estado atualizar(@ApiParam(value = "Id de um Estado", example = "1", required = true) Long estadoId,
			@ApiParam(name = "corpo", value = "Representação de um Estado", required = true) EstadoInputDto estado);

	@ApiOperation("Exclusão de uma cidade")
	@ApiResponses({
		@ApiResponse(code= 409, message = "Não foi possivel excluir o Estado, por causa de erro de conflito", response = ErroApi.class),
		@ApiResponse(code = 400, message = "Não doi possivel excluir o Estado pois o ID esta inválido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Não foi possivel excluir o Estado, pois ela não existe", response = ErroApi.class),
		@ApiResponse(code = 204, message = "Estado excluido com sucesso")
	})
	void deletar(@ApiParam(value = "Id de um Estado", example = "1", required = true) Long estadoId);

}