package com.nesrux.jmfood.api.classconversion.dissasembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.output.produto.ProdutoModel;
import com.nesrux.jmfood.domain.model.pedido.Produto;

@Component
public class ProdutoInputDisassembler {
	@Autowired
	private ModelMapper mapper;

	public Produto toDomainObject(ProdutoModel produtoModel) {
		return mapper.map(produtoModel, Produto.class);
	}

	public void copyToDomainObject(ProdutoModel produtoModel, Produto produto) {
		mapper.map(produtoModel, produto);
	}
}
