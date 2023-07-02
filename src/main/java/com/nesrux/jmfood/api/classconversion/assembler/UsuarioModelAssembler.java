package com.nesrux.jmfood.api.classconversion.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.controller.UsuarioController;
import com.nesrux.jmfood.api.controller.subcontrollers.usuarios.UsuarioGrupoController;
import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;
import com.nesrux.jmfood.domain.model.user.Usuario;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {
	@Autowired
	private ModelMapper mapper;

	public UsuarioModelAssembler() {
		super(Usuario.class, UsuarioModel.class);
	}

	@Override
	public UsuarioModel toModel(Usuario usuario) {
		UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);
		
		mapper.map(usuario, usuarioModel);
		
		//Adiciona links da propria coleção
		usuarioModel.add(linkTo(methodOn(UsuarioController.class).listar()).withRel("Usuarios"));
		
		//Adiciona link para a URI /usuaios/{userId}/grupos
		usuarioModel.add(linkTo(methodOn(UsuarioGrupoController.class).listarGruposUsuario(usuarioModel.getId())).withRel("grupos-usuarios"));
		
		return mapper.map(usuario, UsuarioModel.class);
	}

	@Override
	public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities).add(linkTo(UsuarioController.class).withSelfRel());
	}

}
