package com.nesrux.jmfood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "RootEntryPoint")
public class RootEntryPointController {
	@Autowired
	private JmFoodLinks links;

	@GetMapping
	public RootEntryPointoModel rootEntryPoint() {
		RootEntryPointoModel root = new RootEntryPointoModel();

		root.add(links.linkToCozinhas("cozinhas"));
		root.add(links.linkToRestaurantes("restaurantes"));
		root.add(links.linkToFormasPagamentos("formas-pagamentos"));
		root.add(links.linkToPedidos("pedidos"));
		root.add(links.linkToCidades("cidades"));
		root.add(links.linkToEstados("estados"));
		root.add(links.linkToUsuarios("usuarios"));
		root.add(links.linkToPermissoes("permissoes"));
		root.add(links.linkToGrupos("grupos"));
		root.add(links.linkToEstatisticas("estatisticas"));

		return root;
	}

	private static class RootEntryPointoModel extends RepresentationModel<RootEntryPointoModel> {
	}

}
