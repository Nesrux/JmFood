package com.nesrux.jmfood.api.openapi.controller.permissao;

import org.springframework.hateoas.CollectionModel;

import com.nesrux.jmfood.api.model.dto.output.permissao.PermissaoModel;

public interface PermissaoControllerOpenApi {

	public CollectionModel<PermissaoModel> listarPermissoes();
}
