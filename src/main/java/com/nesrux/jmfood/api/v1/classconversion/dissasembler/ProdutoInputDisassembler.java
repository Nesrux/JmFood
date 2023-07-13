package com.nesrux.jmfood.api.v1.classconversion.dissasembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.model.dto.input.produto.ProdutoInputDto;
import com.nesrux.jmfood.domain.model.pedido.Produto;

@Component
public class ProdutoInputDisassembler {
	@Autowired
	private ModelMapper mapper;

	public Produto toDomainObject(ProdutoInputDto produtoinput) {
		return mapper.map(produtoinput, Produto.class);
	}

	public void copyToDomainObject(ProdutoInputDto produtoInput, Produto produto) {
		mapper.map(produtoInput, produto);
	}
}
