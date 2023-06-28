package com.nesrux.jmfood.api.openapi.controller.restaurante;

import java.util.List;

import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = "Restaurantes")
public interface RestauranteUsuarioControllerOpenApi {
	
	@ApiOperation("Listagem de funcionarios")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id de Restaurante esta inválido"),
		@ApiResponse(code = 404, message = "Não existe Restaurante com o Id fornecido"),
		@ApiResponse(code = 200, message = "Listagem feita com sucesso")
	})
	public List<UsuarioModel> listarFuncionariosRestaurante(@ApiParam(value = "Id de um restaurante", example = "1", required = true) 
		Long restauranteId);
	
	@ApiOperation("Associação de funcionarios")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id de restaurante ou de usuario esta invalido"),
		@ApiResponse(code = 404, message = "Id de restaurante ou de usuario não existem"),
		@ApiResponse(code = 204, message = "Associação feita com sucesso")
	})
	public void associarFuncionarios(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(value = "id de um funcionario", example = "1", required = true)	Long usuarioId);
	
	@ApiOperation("Desassociação de funcionarios")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id de restaurante ou de usuario esta invalido"),
		@ApiResponse(code = 404, message = "Id de restaurante ou de usuario não existem"),
		@ApiResponse(code = 204, message = "Desassociação feita com sucesso")
	})
	public void desassociarFuncionarios(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(value = "id de um funcionario", example = "1", required = true) Long usuarioId);

}
