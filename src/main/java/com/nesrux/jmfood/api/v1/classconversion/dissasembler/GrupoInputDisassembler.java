package com.nesrux.jmfood.api.v1.classconversion.dissasembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.model.dto.input.grupo.GrupoInputDto;
import com.nesrux.jmfood.domain.model.user.Grupo;

@Component
public class GrupoInputDisassembler {
	@Autowired
	private ModelMapper mapper;

	public Grupo toDomainObject(GrupoInputDto inputDto) {
		return mapper.map(inputDto, Grupo.class);
	}

	public void copyToDomainObject(GrupoInputDto inputDto, Grupo grupo) {
		mapper.map(inputDto, grupo);
	}

}
