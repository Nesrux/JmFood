package com.nesrux.jmfood.api.openapi.controller.endereco;

import java.util.List;

import com.nesrux.jmfood.api.model.dto.input.estado.EstadoInputDto;
import com.nesrux.jmfood.api.model.dto.output.estado.EstadoModel;
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
	List<EstadoModel> listar();

	@ApiOperation("Busca de uma unica cidade")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Não foi possivel encontrar o Estado pois o ID esta inválido"),
		@ApiResponse(code = 404, message = "Não foi possivel encontrar o Estado, pois a cidade com este id não existe"),
	})
	EstadoModel buscar(@ApiParam(value = "Id de um Estado", example = "1", required = true)  Long estadoId);

	@ApiOperation("Cadastro de uma cidade")
	EstadoModel adicionar(@ApiParam(name = "corpo", value = "Representação de um Estado", required = true) EstadoInputDto estadoDto);

	@ApiOperation("Atualização de uma cidade")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Não foi possivel atualizar o Estado pois o ID esta inválido"),
		@ApiResponse(code = 404, message = "Não foi possivel atualizar o Estado, pois ela não existe"),
		@ApiResponse(code =  200, message = "Estado atualizado com sucesso")
	})
	Estado atualizar(@ApiParam(value = "Id de um Estado", example = "1", required = true) Long estadoId,
			@ApiParam(name = "corpo", value = "Representação de um Estado", required = true) EstadoInputDto estado);

	@ApiOperation("Exclusão de uma cidade")
	@ApiResponses({
		@ApiResponse(code= 409, message = "Não foi possivel excluir o Estado, por causa de erro de conflito"),
		@ApiResponse(code = 400, message = "Não doi possivel excluir o Estado pois o ID esta inválido"),
		@ApiResponse(code = 404, message = "Não foi possivel excluir o Estado, pois ela não existe"),
		@ApiResponse(code = 204, message = "Estado excluido com sucesso")
	})
	void deletar(@ApiParam(value = "Id de um Estado", example = "1", required = true) Long estadoId);

}