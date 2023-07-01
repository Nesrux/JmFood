package com.nesrux.jmfood.api.springFox.controller.produtos;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.model.dto.input.fotoProduto.FotoProdutoInput;
import com.nesrux.jmfood.api.model.dto.output.fotoProduto.FotoProdutoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteFotoProdutoControllerOpenApi {
	
	@ApiOperation(value = "Atualiza uma foto de um produto de um restaurante")
	@ApiResponses({
		@ApiResponse(code =  400, message = "Ocorreu um erro na requisição, parametros invalidos ou arquivo invalido", response = ErroApi.class),
		@ApiResponse(code =  404, message = "Não foi possivel atualizar pois o Id de restaurante ou de produto nao existem", response = ErroApi.class),
		@ApiResponse(code =  200, message = "Atualizalção de foto feita com sucesso")
	})
	public FotoProdutoModel atualizarFoto(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(value = "Id de um produto", example = "1", required = true) Long produtoId,
			@ApiParam(value = "Arquivo da foto do produto (max 500kb, apenas JPEG e PNG)", required = true) MultipartFile arquivo,
			FotoProdutoInput fotoProdutoInput)
			throws IOException;
	
	@ApiOperation(value = "Busca uma foto de um produto de um restaurante", produces = "application/json, image/jpeg, image/png")
	@ApiResponses({
		@ApiResponse(code =  400, message = "Ocorreu um erro na requisição, parametros invalidos ou arquivo invalido", response = ErroApi.class),
		@ApiResponse(code =  404, message = "Não foi possivel buscar pois o Id de restaurante ou de produto nao existem", response = ErroApi.class),
		@ApiResponse(code =  200, message = "Busca de foto feita com sucesso")
	})
	public FotoProdutoModel buscarFoto(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(value = "Id de um produto", example = "1", required = true) Long produtoId);

	
	@ApiOperation(value = "Busca uma foto de um produto de um restaurante", hidden = true)
	public ResponseEntity<?> servirFoto(@ApiParam(value = "Id de um restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(value = "Id de um produto", example = "1", required = true) Long produtoId,
			String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;

	@ApiOperation(value = "Exclui uma foto de um produto de um restaurante")
	@ApiResponses({
		@ApiResponse(code =  400, message = "Id do restaurante ou do produto inválido", response = ErroApi.class),
		@ApiResponse(code =  404, message = "Não foi possivel excluir pois o Id de restaurante ou de produto nao existem", response = ErroApi.class),
		@ApiResponse(code =  204, message = "Exclusão de foto feita com sucesso")
	})
	public void excluirFoto(@ApiParam(value = "Id de um restaurante", example = "1", required = true)Long restauranteId,
			@ApiParam(value = "Id de um produto", example = "1", required = true) Long produtoId);

}
