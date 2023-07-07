package com.nesrux.jmfood.api.classconversion.assembler.produto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.output.produto.ProdutoModel;
import com.nesrux.jmfood.domain.model.pedido.Produto;

@Component
public class ProdutoModelAssembler {
	@Autowired
	private ModelMapper mapper;

	public ProdutoModel toModel(Produto produto) {
		return mapper.map(produto, ProdutoModel.class);
	}

	public List<ProdutoModel> toCollectionDto(List<Produto> produtos) {
		return produtos.stream().map(produto -> toModel(produto)).collect(Collectors.toList());
	}

}
