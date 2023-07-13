package com.nesrux.jmfood.api.v1.openapi.controller.grupos;

import org.springframework.hateoas.CollectionModel;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.v1.model.dto.input.grupo.GrupoInputDto;
import com.nesrux.jmfood.api.v1.model.dto.output.grupo.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {
	@ApiOperation("Listagem de grupos")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Listagem feita com sucesso") })
	public CollectionModel<GrupoModel> listar();

	@ApiOperation("Busca de um grupo")
	@ApiResponses({ 
			@ApiResponse(code = 404, message = "Um grupo com este Id não existe", response = ErroApi.class),
			@ApiResponse(code = 400, message = "Id de grupo esta inválido", response = ErroApi.class),
			@ApiResponse(code = 200, message = "Busca feita com sucesso") })
	public GrupoModel buscar(@ApiParam(example = "1", value = "Id de um grupo", required = true) Long grupoId);

	@ApiOperation("Cadastro de um grupo")
	@ApiResponses({
		@ApiResponse(code= 400, message = "Representação do grupo esta inválido", response = ErroApi.class),
		@ApiResponse(code= 200, message = "Grupo cadastrado com sucesso"),
	})
	public GrupoModel salvar(
			@ApiParam(name = "corpo", value = "Representação de um grupo", required = true) GrupoInputDto inpDto);

	@ApiOperation("Exclusão de um grupo")
	@ApiResponses({
		@ApiResponse(code = 409, message = "Exclusão de grupo não pode ser feito, Erro de conflito", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Não existe um grupo cadastrado com este ID", response = ErroApi.class),
		@ApiResponse(code = 400, message = "Id de grupo inválido", response = ErroApi.class),
		@ApiResponse(code = 204, message = "Grupo apagado com sucesso"),
	})
	public void excluir(@ApiParam(example = "1", value = "Id de um grupo", required = true) Long grupoId);

	@ApiOperation("Atualização de um grupo")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Não existe um grupo cadastrado com este ID", response = ErroApi.class),
		@ApiResponse(code = 400, message = "Id de grupo inválido", response = ErroApi.class),
		@ApiResponse(code = 200, message = "Grupo Atualizado com sucesso"),
	})
	public GrupoModel atualizar(
			@ApiParam(name = "corpo", value = "Representação de um grupo", required = true) GrupoInputDto inputDto,
			@ApiParam(example = "1", value = "Id de um grupo", required = true) Long grupoId);
}
