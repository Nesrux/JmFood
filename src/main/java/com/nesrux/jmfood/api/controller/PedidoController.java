package com.nesrux.jmfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.PedidoModelAssembler;
import com.nesrux.jmfood.api.classconversion.assembler.PedidoResumoModelAssembler;
import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoModel;
import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoResumoModel;
import com.nesrux.jmfood.domain.model.pedido.Pedido;
import com.nesrux.jmfood.domain.service.CadastroPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	private CadastroPedidoService service;
	@Autowired
	private PedidoModelAssembler assembler;
	@Autowired
	private PedidoResumoModelAssembler resumoAssembler;

	@GetMapping
	public List<PedidoResumoModel> listarPedidos() {
		return resumoAssembler.toCollectionDto(service.Listar());
	}

	@GetMapping("/{pedidoId}")
	public PedidoModel buscar(@PathVariable Long pedidoId) {
		Pedido pedido = service.acharOuFalhar(pedidoId);
		return assembler.toModel(pedido);
	}

}
