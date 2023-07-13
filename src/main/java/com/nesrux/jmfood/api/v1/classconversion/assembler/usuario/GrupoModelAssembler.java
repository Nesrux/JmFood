package com.nesrux.jmfood.api.v1.classconversion.assembler.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.controller.main.GrupoController;
import com.nesrux.jmfood.api.v1.model.dto.output.grupo.GrupoModel;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.domain.model.user.Grupo;

@Component
public class GrupoModelAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoModel> {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JmFoodLinks links;

	public GrupoModelAssembler() {
		super(GrupoController.class, GrupoModel.class);
	}

	@Override
	public GrupoModel toModel(Grupo grupo) {
		GrupoModel grupoModel = createModelWithId(grupo.getId(), grupo);
		mapper.map(grupo, grupoModel);
		grupoModel.add(links.linkToGrupos("grupos"));
		grupoModel.add(links.linkToGrupoPermissao(grupoModel.getId(), "permissoes"));
		return grupoModel;
	}

	@Override
	public CollectionModel<GrupoModel> toCollectionModel(Iterable<? extends Grupo> entities) {
		return super.toCollectionModel(entities).add(links.linkToGrupos());
	}

}
