package com.nesrux.jmfood.api.v1.openapi.controller.usuarios;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.nesrux.jmfood.api.v1.model.dto.output.grupo.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuarios")
public interface UsuarioGrupoControllerOpenApi {

	@ApiOperation("Lista os grupos de um usuario")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id de usuario inválido"),
		@ApiResponse(code = 404, message = "Id de usuario não existe"),
		@ApiResponse(code = 200, message = "Listagem de grupos feito com sucesso")
	})
	public CollectionModel<GrupoModel> listarGruposUsuario(@ApiParam(example = "1", required = true) Long usuarioId);

	@ApiOperation("Associa um usuario a um grupo")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id de usuario ou de grupo estão invalidos"),
		@ApiResponse(code = 404, message = "Id de usuario ou de grupo não existem"),
		@ApiResponse(code = 204, message = "Associação feita com sucesso")
	})
	public ResponseEntity<Void> associarGrupo(@ApiParam(example = "1", required = true) Long usuarioId,
			@ApiParam(example = "1", value = "Id de uma permissao", required = true) Long grupoId);

	@ApiOperation("desassocia um grupo a um usuario")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id de usuario ou de grupo estão invalidos"),
		@ApiResponse(code = 404, message = "Id de usuario ou de grupo não existem"),
		@ApiResponse(code = 204, message = "Desassociação feita com sucesso")
	})
	public ResponseEntity<Void> desassociarGrupo(@ApiParam(example = "1", required = true) Long usuarioId,
			@ApiParam(example = "1", value = "Id de uma permissao", required = true) Long grupoId);

}
