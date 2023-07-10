package com.nesrux.jmfood.api.openapi.controller.grupos;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.model.dto.output.permissao.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenapi {
	
	@ApiResponses({
		@ApiResponse(code = 400, message = "Codigo de grupo inválido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "codigo de grupo não existe", response = ErroApi.class),
		@ApiResponse(code = 200, message = "Listagem feita com sucesso")
	})	
	@ApiOperation("Lista todas as permissoes de um grupo")
	public CollectionModel<PermissaoModel> ListarPermissoes(Long grupoId);

	@ApiOperation("Busca uma permissão de um grupo")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Codigo de grupo ou de permissão estão inválidos", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Codigo de grupo ou de permissão não existem", response = ErroApi.class),
		@ApiResponse(code = 200, message = "Busca feita com sucesso")
	})
	public PermissaoModel buscarPermissao(
			@ApiParam(example = "1", value = "Id de um grupo", required = true) Long grupoId,
			@ApiParam(example = "1", value = "Id de uma permissao", required = true) Long permissaoId);

	
	@ApiResponses({
		@ApiResponse(code = 400, message = "Codigo de grupo ou de permissão estão inválidos", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Codigo de grupo ou de permissão não existem", response = ErroApi.class),
		@ApiResponse(code = 204, message = "Desassociação feita com sucesso")
	})
	@ApiOperation("Desassocia uma permissão de um grupo")
	public ResponseEntity<Void> dessassociarPermissao(
			@ApiParam(example = "1", value = "Id de um grupo", required = true) Long grupoId,
			@ApiParam(example = "1", value = "Id de uma permissao", required = true) Long permissaoId);

	
	@ApiResponses({
		@ApiResponse(code = 400, message = "Codigo de grupo ou de permissão estão inválidos", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Codigo de grupo ou de permissão não existem", response = ErroApi.class),
		@ApiResponse(code = 204, message = "Associação feita com sucesso")
	})
	@ApiOperation("Associa uma permissão a um grupo")
	public ResponseEntity<Void> associarPermissao(
			@ApiParam(example = "1", value = "Id de um grupo", required = true) Long grupoId,
			@ApiParam(example = "1", value = "Id de uma permissao", required = true) Long permissaoId);

}
