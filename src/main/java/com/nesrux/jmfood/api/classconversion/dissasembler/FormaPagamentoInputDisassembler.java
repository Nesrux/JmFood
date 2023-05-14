package com.nesrux.jmfood.api.classconversion.dissasembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.input.formaPagamento.FormaPagamentoInputDto;
import com.nesrux.jmfood.domain.model.pedido.FormaPagamento;

@Component
public class FormaPagamentoInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public FormaPagamento toDomainObject(FormaPagamentoInputDto inputDto) {
		return mapper.map(inputDto, FormaPagamento.class);
	}
	
	public void copyToDomainObject(FormaPagamentoInputDto inputDto, FormaPagamento formaPagamento) {
		mapper.map(inputDto, formaPagamento);
	}
	
}
