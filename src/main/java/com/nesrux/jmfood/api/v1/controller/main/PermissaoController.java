package com.nesrux.jmfood.api.v1.controller.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.v1.classconversion.assembler.usuario.PermissaoModelAssembler;
import com.nesrux.jmfood.api.v1.model.dto.output.permissao.PermissaoModel;
import com.nesrux.jmfood.api.v1.openapi.controller.permissao.PermissaoControllerOpenApi;
import com.nesrux.jmfood.domain.service.CadastroPermissaoService;

@RestController
@RequestMapping(path = "/v1/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {

	@Autowired
	private PermissaoModelAssembler assembler;

	@Autowired
	private CadastroPermissaoService service;

	@Override
	@GetMapping
	public CollectionModel<PermissaoModel> listarPermissoes() {
		return assembler.toCollectionModel(service.listar());
	}
}
