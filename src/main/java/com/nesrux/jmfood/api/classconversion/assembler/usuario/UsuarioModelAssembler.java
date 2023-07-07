package com.nesrux.jmfood.api.classconversion.assembler.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.controller.UsuarioController;
import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;
import com.nesrux.jmfood.api.utils.JmFoodLinks;
import com.nesrux.jmfood.domain.model.user.Usuario;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JmFoodLinks links;

	public UsuarioModelAssembler() {
		super(UsuarioController.class, UsuarioModel.class);
	}

	@Override
	public UsuarioModel toModel(Usuario usuario) {
		UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);

		mapper.map(usuario, usuarioModel);

		// Adiciona links da propria coleção
		usuarioModel.add(links.linktoUsuario());

		// Adiciona link para a URI /usuaios/{userId}/grupos
		usuarioModel.add(links.linkToGrupoUsuario(usuarioModel.getId()));

		return usuarioModel;
	}

	@Override
	public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities).add(links.linktoUsuario());
	}

}
