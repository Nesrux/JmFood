package com.nesrux.jmfood.api.v1.openapi.controller.permissao;

import org.springframework.hateoas.CollectionModel;

import com.nesrux.jmfood.api.v1.model.dto.output.permissao.PermissaoModel;

public interface PermissaoControllerOpenApi {

	public CollectionModel<PermissaoModel> listarPermissoes();
}
