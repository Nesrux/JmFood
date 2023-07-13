package com.nesrux.jmfood.api.v1.classconversion.assembler.restaurante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.controller.FormaPagamentoController;
import com.nesrux.jmfood.api.v1.model.dto.output.formaPagamento.FormaPagamentoModel;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.domain.model.pedido.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler
		extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel> {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private JmFoodLinks links;

	public FormaPagamentoModelAssembler() {
		super(FormaPagamentoController.class, FormaPagamentoModel.class);
	}

	@Override
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		FormaPagamentoModel formaPagamentoModel = createModelWithId(formaPagamento.getId(), formaPagamento);
		mapper.map(formaPagamento, formaPagamentoModel);
	
		formaPagamentoModel.add(links.linkToFormasPagamentos("formas-pagamentos"));
		return formaPagamentoModel;
	}

	@Override
	public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
		return super.toCollectionModel(entities).add(links.linkToFormasPagamentos());
	}

}
