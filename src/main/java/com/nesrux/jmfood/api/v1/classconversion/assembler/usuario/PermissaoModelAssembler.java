package com.nesrux.jmfood.api.v1.classconversion.assembler.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.controller.main.PermissaoController;
import com.nesrux.jmfood.api.v1.model.dto.output.permissao.PermissaoModel;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.domain.model.user.Permissao;

@Component
public class PermissaoModelAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoModel> {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private JmFoodLinks links;

	public PermissaoModelAssembler() {
		super(PermissaoController.class, PermissaoModel.class);
	}

	public PermissaoModel toModel(Permissao permissao) {
		return mapper.map(permissao, PermissaoModel.class);
	}

	@Override
	public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
		return super.toCollectionModel(entities).add(links.linkToPermissoes());
	}
}
