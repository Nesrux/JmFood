package com.nesrux.jmfood.api.openapi.controller.usuarios;

import java.util.List;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.model.dto.input.usuario.TrocarSenhaInput;
import com.nesrux.jmfood.api.model.dto.input.usuario.UsuarioInput;
import com.nesrux.jmfood.api.model.dto.input.usuario.UsuarioInputAtualizar;
import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuarios")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Listagem de usuarios")
	@ApiResponses({ @ApiResponse(code = 200, message = "Listagem feita com sucesso") })
	public List<UsuarioModel> listar();

	@ApiOperation("Busca de um unico usuario")
	@ApiResponses({ @ApiResponse(code = 400, message = "Codigo de usuario inválido", response = ErroApi.class),
			@ApiResponse(code = 404, message = "Id de usuario não existe", response = ErroApi.class),
			@ApiResponse(code = 200, message = "Busca de usuario feito com sucesso") })
	public UsuarioModel buscar(@ApiParam(example = "1", required = true) Long usuarioId);

	@ApiOperation("Cadastro de um usuario")
	@ApiResponses({ @ApiResponse(code = 400, message = "representação de usuario inválida", response = ErroApi.class),
			@ApiResponse(code = 200, message = "cadastro de usuario feito com sucesso") })
	public UsuarioModel salvar(
			@ApiParam(name = "Corpo", value = "Representação de um usuario", required = true) UsuarioInput userInput);

	@ApiOperation("Atualização de um usuario")
	@ApiResponses({
			@ApiResponse(code = 400, message = "Codigo de usuario  ou representação de usuario inválido", response = ErroApi.class),
			@ApiResponse(code = 404, message = "Id de usuario não existe", response = ErroApi.class),
			@ApiResponse(code = 200, message = "Atualização de usuario feita com sucesso") })
	public UsuarioModel atualizar(@ApiParam(example = "1", required = true) Long usuarioId,
			@ApiParam(name = "Corpo", value = "Representação de uma atualização de um usuario", required = true) UsuarioInputAtualizar usuarioInput);

	@ApiOperation("Atualização de senha de um usuario")
	@ApiResponses({
			@ApiResponse(code = 400, message = "Codigo de usuario ou representação de senha inválido", response = ErroApi.class),
			@ApiResponse(code = 404, message = "Id de usuario não existe", response = ErroApi.class),
			@ApiResponse(code = 204, message = "Atualização de senha feita com sucesso") })
	public void atualizarSenha(@ApiParam(example = "1", required = true) Long usuarioId,
			@ApiParam(name = "Corpo", value = "Representação de senhas do usuario", required = true) TrocarSenhaInput senhainput);

}
