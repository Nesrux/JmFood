package com.nesrux.jmfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.nesrux.jmfood.api.classconversion.assembler.PedidoModelAssembler;
import com.nesrux.jmfood.api.classconversion.assembler.PedidoResumoModelAssembler;
import com.nesrux.jmfood.api.classconversion.dissasembler.PedidoInputDisasselber;
import com.nesrux.jmfood.api.model.dto.input.pedido.PedidoInputDto;
import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoModel;
import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoResumoModel;
import com.nesrux.jmfood.core.data.PageableTranslator;
import com.nesrux.jmfood.domain.filter.PedidoFilter;
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

	@Autowired
	private PedidoInputDisasselber pedidoDisasselber;

	@GetMapping
	public Page<PedidoResumoModel> pesquisarPedidos(PedidoFilter filter, @PageableDefault(size = 10) Pageable page) {
		page = traduzirPageable(page);
		Page<Pedido> pedidosPage = service.Listar(filter, page);
		List<PedidoResumoModel> pedidosModel = resumoAssembler.toCollectionDto(pedidosPage.getContent());

		Page<PedidoResumoModel> pagePedidoModel = new PageImpl<>(pedidosModel, page, pedidosPage.getTotalElements());

		return pagePedidoModel;
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

	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = ImmutableMap.of("codigo", "codigo",
				"restaurante.nome", "restaurante.nome",
				"cliente.nome", "cliente.nome",
				"valorTotal", "valorTotal");

		return PageableTranslator.translate(apiPageable, mapeamento);
	}
}
