package com.nesrux.jmfood.api.v1.openapi.controller.restaurante;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.v1.model.dto.output.usuario.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = "Restaurantes")
public interface RestauranteUsuarioControllerOpenApi {
	
	@ApiOperation("Listagem de funcionarios")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id de Restaurante esta inválido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Não existe Restaurante com o Id fornecido", response = ErroApi.class),
		@ApiResponse(code = 200, message = "Listagem feita com sucesso")
	})
	public CollectionModel<UsuarioModel> listarFuncionariosRestaurante(@ApiParam(value = "Id de um restaurante", example = "1", required = true) 
		Long restauranteId);
	
	@ApiOperation("Associação de funcionarios")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id de restaurante ou de usuario esta invalido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Id de restaurante ou de usuario não existem", response = ErroApi.class),
		@ApiResponse(code = 204, message = "Associação feita com sucesso")
	})
	public ResponseEntity<Void> associarFuncionarios(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(value = "id de um funcionario", example = "1", required = true)	Long usuarioId);
	
	@ApiOperation("Desassociação de funcionarios")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id de restaurante ou de usuario esta invalido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Id de restaurante ou de usuario não existem", response = ErroApi.class),
		@ApiResponse(code = 204, message = "Desassociação feita com sucesso")
	})
	public ResponseEntity<Void> desassociarFuncionarios(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(value = "id de um funcionario", example = "1", required = true) Long usuarioId);

}
