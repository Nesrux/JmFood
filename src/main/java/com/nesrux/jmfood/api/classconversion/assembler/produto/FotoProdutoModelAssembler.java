package com.nesrux.jmfood.api.classconversion.assembler.produto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.output.fotoProduto.FotoProdutoModel;
import com.nesrux.jmfood.domain.model.pedido.FotoProduto;
@Component
public class FotoProdutoModelAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public FotoProdutoModel toModel(FotoProduto fotoProduto) {
		return mapper.map(fotoProduto, FotoProdutoModel.class);
	}

}
