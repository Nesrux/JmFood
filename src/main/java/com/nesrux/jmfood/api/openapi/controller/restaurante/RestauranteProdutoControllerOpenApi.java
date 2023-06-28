package com.nesrux.jmfood.api.openapi.controller.restaurante;

import java.util.List;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.model.dto.input.produto.ProdutoInputDto;
import com.nesrux.jmfood.api.model.dto.output.produto.ProdutoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteProdutoControllerOpenApi {

	@ApiOperation("Lista todos os produtos cadastrados em um restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "o código do restaurante esta inválido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Não possivel Listar pois não existe Restaurante com  este código", response = ErroApi.class),
		@ApiResponse(code = 200, message = "Listagem feita com sucesso")
	})
	public List<ProdutoModel> listar(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId,
		@ApiParam(value = "boelano", example = "true")	boolean incluirInativos);
	
	
	@ApiOperation("Busca um unico Produto cadastrado em um Restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "o código do restaurante ou do produto esta inválido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Não possivel buscar pois não existe Restaurante ou produto com  este código", response = ErroApi.class),
		@ApiResponse(code = 200, message = "Busca feita com sucesso")
	})
	public ProdutoModel buscar(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(value = "Representação de um Produto", example = "1", required = true) Long produtoId);
	
	
	@ApiOperation("Salva um novo produto dentro de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "o corpo do produto  ou o código do restaurante esta inválido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Não possivel salvar pois não existe Restaurante com  este código", response = ErroApi.class),
		@ApiResponse(code = 200, message = "Cadastro feito com sucesso")
	})
	public ProdutoModel salvar(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(name = "Corpo", value = "Representação de um Produto", required = true) ProdutoInputDto inputDto);


	@ApiOperation("Atualiza um produto de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "o código do restaurante ou do produto esta inválido", response = ErroApi.class),
		@ApiResponse(code = 404, message = "Não possivel atualizar pois não existe Restaurante ou produto com  este código", response = ErroApi.class),
		@ApiResponse(code = 200, message = "Atualização feita com sucesso")
	})
	public ProdutoModel atualizar(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long produtoId,
			@ApiParam(name = "Corpo", value = "Representação de um Produto", required = true) ProdutoInputDto inputDto);






}
