package com.nesrux.jmfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.PedidoModelAssembler;
import com.nesrux.jmfood.api.classconversion.assembler.PedidoResumoModelAssembler;
import com.nesrux.jmfood.api.classconversion.dissasembler.PedidoInputDisasselber;
import com.nesrux.jmfood.api.model.dto.input.pedido.PedidoInputDto;
import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoModel;
import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoResumoModel;
import com.nesrux.jmfood.domain.model.pedido.Pedido;
import com.nesrux.jmfood.domain.repository.filter.PedidoFilter;
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

	@Autowired
	private PedidoInputDisasselber pedidoDisasselber;

	@GetMapping
	public List<PedidoResumoModel> pesquisarPedidos(PedidoFilter filter) {
		return resumoAssembler.toCollectionDto(service.Listar(filter));
	}

	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = service.acharOuFalhar(codigoPedido);
		return assembler.toModel(pedido);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel salvar(@Valid @RequestBody PedidoInputDto inputDto) {
		Pedido pedido = pedidoDisasselber.toDomainObject(inputDto);
		return assembler.toModel(service.emitir(pedido));
	}

}
