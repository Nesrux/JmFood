package com.nesrux.jmfood.api.v1.openapi.controller.restaurante;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.v1.model.dto.input.restaurante.RestauranteInputDto;
import com.nesrux.jmfood.api.v1.model.dto.output.restaurante.RestauranteApenasNomeModel;
import com.nesrux.jmfood.api.v1.model.dto.output.restaurante.RestauranteBasicoModel;
import com.nesrux.jmfood.api.v1.model.dto.output.restaurante.RestauranteModel;
import com.nesrux.jmfood.api.v1.openapi.model.RestauranteBasicoOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

	@ApiOperation(value = "Listagem de restaurantes", response = RestauranteBasicoOpenApi.class)
	@ApiImplicitParams({
			@ApiImplicitParam(value = "nome da projeção de pedidos", allowableValues = "apenas-nome", name = "projeção", paramType = "query", type = "string") })
	@ApiResponses({ @ApiResponse(code = 200, message = "A listagem de pedidos foi feito com sucesso") })
	public CollectionModel<RestauranteBasicoModel> listarResumo();

	@ApiIgnore
	@ApiOperation(value = "Listagem de restaurantes", hidden = true)
	@ApiResponses({ @ApiResponse(code = 200, message = "A listagem de pedidos foi feito com sucesso") })
	public CollectionModel<RestauranteApenasNomeModel> listarNomes();

	@ApiOperation("Busca de um unico restaurante")
	@ApiResponses({ @ApiResponse(code = 404, message = "Não foi possivel encontrar um restaurante com o id informado"),
			@ApiResponse(code = 400, message = "Id do restaurante esta inválido"), })
	public RestauranteModel buscar(
			@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId);

	@ApiOperation("Cadastro de um restaurante")
	@ApiResponses({
			@ApiResponse(code = 400, message = "Representação de Restaurante esta inválida", response = ErroApi.class),
			@ApiResponse(code = 200, message = "Cadastro de restaurante feito com sucesso"), })
	public RestauranteModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um restaurante", required = true) RestauranteInputDto restauranteInputDTO);

	@ApiOperation("Atualização de um restaurante")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Não foi possivel encontrar um restaurante com o id informado", response = ErroApi.class),
			@ApiResponse(code = 400, message = "Id do restaurante ou a representação de restaurante estão inválidos", response = ErroApi.class),
			@ApiResponse(code = 200, message = "Atualização feita com sucesso"), })
	public RestauranteModel atualizar(
			@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(name = "corpo", value = "Representação de um restaurante", required = true) RestauranteInputDto restauranteInputDto);

	@ApiOperation("Ativação de restaurantes em massa")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Não foi possivel encontrar um restaurante com o id informado", response = ErroApi.class),
			@ApiResponse(code = 400, message = "Id do restaurante esta invalido", response = ErroApi.class),
			@ApiResponse(code = 204, message = "Restaurantes ativados com sucesso") })
	public ResponseEntity<Void> ativarRestaurantes(List<Long> restaurantes);

	@ApiOperation("desativação de restaurantes em massa")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Não foi possivel encontrar um restaurante com o id passado", response = ErroApi.class),
			@ApiResponse(code = 400, message = "Id do restaurante é invalido", response = ErroApi.class),
			@ApiResponse(code = 204, message = "Desativação de restaurantes feito com sucesso") })
	public ResponseEntity<Void> desativarRestaurantes(
			@ApiParam(value = "Lista de Ids que serão desativados", example = "1") List<Long> restauranteids);

	@ApiOperation("Ativação de um unico restaurante")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Não foi possivel encontrar um restaurante com o id passado", response = ErroApi.class),
			@ApiResponse(code = 400, message = "Id do restaurante é invalido", response = ErroApi.class),
			@ApiResponse(code = 204, message = "Ativação de restaurante feito com sucesso") })
	public ResponseEntity<Void> ativarRestaurante(
			@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId);

	@ApiOperation("Desativação de um unico restaurante")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Não foi possivel encontrar um restaurante com o id passado", response = ErroApi.class),
			@ApiResponse(code = 400, message = "Id do restaurante é invalido", response = ErroApi.class),
			@ApiResponse(code = 204, message = "Desativação de restaurante feito com sucesso") })
	public ResponseEntity<Void> desativarRestaurante(
			@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId);

	@ApiOperation("Fechamento de um unico restaurante")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Não foi possivel encontrar um restaurante com o id passado", response = ErroApi.class),
			@ApiResponse(code = 400, message = "Id do restaurante é invalido", response = ErroApi.class),
			@ApiResponse(code = 400, message = "Fechamendo do restaurante feito com sucesso") })
	public ResponseEntity<Void> fecharRestaurante(
			@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId);

	@ApiOperation("Abertura de um unico restaurante")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Não foi possivel encontrar um restaurante com o id passado", response = ErroApi.class),
			@ApiResponse(code = 400, message = "Id do restaurante é invalido", response = ErroApi.class),
			@ApiResponse(code = 204, message = "Abertura de restaurante feito com sucesso") })
	public ResponseEntity<Void> abrirRestaurante(
			@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId);

}
