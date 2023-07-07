package com.nesrux.jmfood.api.classconversion.assembler.usuario;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.output.grupo.GrupoModel;
import com.nesrux.jmfood.domain.model.user.Grupo;

@Component
public class GrupoModelAssembler {
	@Autowired
	private ModelMapper mapper;

	public GrupoModel toModel(Grupo grupo) {
		return mapper.map(grupo,GrupoModel.class);
	}
	
	public List<GrupoModel> toCollectionDto(List<Grupo> grupos){
		return grupos.stream()
				.map(grupo -> toModel(grupo)).collect(Collectors.toList());
		}
	
	public List<GrupoModel> toCollectionDto(Collection<Grupo> grupos){
		return grupos.stream()
				.map(grupo -> toModel(grupo)).collect(Collectors.toList());
		}
	
}
