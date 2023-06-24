package com.nesrux.jmfood.api.openapi.interfaces;

import java.util.List;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.model.dto.input.grupo.GrupoInputDto;
import com.nesrux.jmfood.api.model.dto.output.grupo.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {
	@ApiOperation("Listagem de grupos")
	public List<GrupoModel> listar();

	@ApiOperation("Busca de um grupo")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Id do grupo inválido", response = ErroApi.class) })
	public GrupoModel buscar(@ApiParam(example = "1", value = "Id de um grupo") Long grupoId);

	@ApiOperation("Cadastro de um grupo")
	public GrupoModel salvar(@ApiParam(name = "corpo", value = "Representação de um grupo") GrupoInputDto inpDto);

	@ApiOperation("Exclusão de um grupo")
	public void excluir(@ApiParam(example = "1", value = "Id de um grupo") Long grupoId);

	@ApiOperation("Atualização de um grupo")
	public GrupoModel atualizar(@ApiParam(name = "corpo", value = "Representação de um grupo") GrupoInputDto inputDto,
			@ApiParam(example = "1", value = "Id de um grupo") Long grupoId);
}
