package com.nesrux.jmfood.api.classconversion.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.output.formaPagamento.FormaPagamentoModel;
import com.nesrux.jmfood.domain.model.pedido.FormaPagamento;

@Component
public class FormaPagamentoOutputAssembler {
	@Autowired
	private ModelMapper mapper;

	public List<FormaPagamentoModel> toCollectionDto(Collection<FormaPagamento> formaPagamentos) {
		return formaPagamentos.stream().map(formaPagamento -> toModel(formaPagamento)).collect(Collectors.toList());
	}

	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		return mapper.map(formaPagamento, FormaPagamentoModel.class);
	}

}
