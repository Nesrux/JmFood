package com.nesrux.jmfood.api.classconversion.dissasembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.input.usuario.UsuarioInput;
import com.nesrux.jmfood.api.model.dto.input.usuario.UsuarioInputAtualizar;
import com.nesrux.jmfood.domain.model.user.Usuario;

@Component
public class UsuarioInputDissasembler {
	@Autowired
	private ModelMapper mapper;

	public Usuario toDomainObject(UsuarioInput input) {
		return mapper.map(input, Usuario.class);
	}

	public void copyToDomainObject(UsuarioInputAtualizar input, Usuario usuario) {
		mapper.map(input, usuario);
	}

}
