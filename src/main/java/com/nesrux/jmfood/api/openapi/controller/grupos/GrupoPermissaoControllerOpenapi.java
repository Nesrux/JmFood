package com.nesrux.jmfood.api.openapi.controller.grupos;

import java.util.List;

import com.nesrux.jmfood.api.model.dto.output.permissao.PermissaoModel;

public interface GrupoPermissaoControllerOpenapi {

	public List<PermissaoModel> ListarPermissoes( Long grupoId);

	public PermissaoModel buscarPermissao( Long grupoId,  Long permissaoId);

	public void dessassociarPermissao( Long grupoId,  Long permissaoId);

	public void associarPermissao( Long grupoId,  Long permissaoId);

}
