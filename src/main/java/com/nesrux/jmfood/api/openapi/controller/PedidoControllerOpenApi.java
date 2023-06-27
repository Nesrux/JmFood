package com.nesrux.jmfood.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nesrux.jmfood.api.model.dto.input.pedido.PedidoInputDto;
import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoModel;
import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoResumoModel;
import com.nesrux.jmfood.domain.filter.PedidoFilter;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

public interface PedidoControllerOpenApi {

	@ApiImplicitParams({
			@ApiImplicitParam(name = "Campos", value = "Nomes das propriedades para filtrar na resposta, separados por virgula",
					paramType = "query",
					example = "codigo") })
	public Page<PedidoResumoModel> pesquisarPedidos(PedidoFilter filter, Pageable page);

	@ApiImplicitParams({
			@ApiImplicitParam(name = "Campos",
					value = "Nomes das propriedades para filtrar na resposta, separados por virgula",
					paramType = "query", example = "codigo") })
	public PedidoModel buscar(String codigoPedido);

	public PedidoModel adicionar(PedidoInputDto pedidoInput);



}
