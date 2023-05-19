package com.nesrux.jmfood.api.classconversion.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.output.permissao.PermissaoModel;
import com.nesrux.jmfood.domain.model.user.Permissao;

@Component
public class PermissaoModelAssembler {

	@Autowired
	private ModelMapper mapper;

	public PermissaoModel toModel(Permissao permissao) {
		return mapper.map(permissao, PermissaoModel.class);
	}

	public List<PermissaoModel> toCollectionDto(List<Permissao> permissoes) {
		return permissoes.stream().map(permissao -> toModel(permissao)).collect(Collectors.toList());
	}
}
