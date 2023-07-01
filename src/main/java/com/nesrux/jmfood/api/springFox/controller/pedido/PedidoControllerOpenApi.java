package com.nesrux.jmfood.api.springFox.controller.pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.model.dto.input.pedido.PedidoInputDto;
import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoModel;
import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoResumoModel;
import com.nesrux.jmfood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@ApiImplicitParams({
			@ApiImplicitParam(
					name = "Campos",
					value = "Nomes das propriedades para filtrar na resposta, separados por virgula",
					paramType = "query",
					example = "codigo") })
	
	@ApiOperation("Listagem de pedidos")
	
	@ApiResponses({ @ApiResponse(code = 200, message = "Pedidos encontrados com sucesso")})
	public Page<PedidoResumoModel> pesquisarPedidos(PedidoFilter filter, Pageable page);

	@ApiImplicitParams({
			@ApiImplicitParam(
					name = "Campos",
					value = "Nomes das propriedades para filtrar na resposta, separados por virgula",
					paramType = "query",
					example = "codigo") })
	@ApiOperation("Busca de um unico pedido")
	@ApiResponses({ 
			@ApiResponse(code = 400, message = "Codigo do pedido é invalido", response = ErroApi.class),
			@ApiResponse(code = 404, message = "Não foi possivel encontrar o pedido com o ID passado", response = ErroApi.class),
			@ApiResponse(code = 200, message = "Pedido encontrado com sucesso") })
	public PedidoModel buscar(
			@ApiParam(value = "Codigo de um pedido", example = "d178b637-a785-4768-a3cb-aa1ce5a8cdab", required = true) String codigoPedido);

	@ApiOperation("Cadastro de um pedido")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Pedido Criado com sucesso"),
		@ApiResponse(code = 400 , message = "Representação de pedido esta inválido", response = ErroApi.class)
		})
	public PedidoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um pedido", required = true) PedidoInputDto pedidoInput);

}
