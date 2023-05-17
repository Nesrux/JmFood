package com.nesrux.jmfood.api.classconversion.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;
import com.nesrux.jmfood.domain.model.user.Usuario;

@Component
public class UsuarioOutputAssembler {
	@Autowired
	private ModelMapper mapper;

	public UsuarioModel toModel(Usuario usuario) {
		return mapper.map(usuario, UsuarioModel.class);
	}

	public List<UsuarioModel> toCollectionDto(List<Usuario> usuarios) {
		return usuarios.stream().map(usuario -> toModel(usuario)).collect(Collectors.toList());
	}

}
