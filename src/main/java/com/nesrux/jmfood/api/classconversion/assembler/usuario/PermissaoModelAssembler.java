package com.nesrux.jmfood.api.classconversion.assembler.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.controller.PermissaoController;
import com.nesrux.jmfood.api.model.dto.output.permissao.PermissaoModel;
import com.nesrux.jmfood.domain.model.user.Permissao;

@Component
public class PermissaoModelAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoModel> {

	@Autowired
	private ModelMapper mapper;

	public PermissaoModelAssembler() {
		super(PermissaoController.class, PermissaoModel.class);
	}

	public PermissaoModel toModel(Permissao permissao) {
		PermissaoModel permissaoModel = createModelWithId(permissao.getId(), permissao);

		mapper.map(permissao, permissaoModel);

		return permissaoModel;
	}

}
