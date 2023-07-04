package com.nesrux.jmfood.api.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.controller.PedidoController;

@Component
public class JmFoodLinks {
	public static final TemplateVariables pagesVariables = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));

	public Link linkToPedidos() {
		// parametros de paginação

		// parametros de filtro das propriedades
		TemplateVariables filtroVariable = new TemplateVariables(
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacao", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataFinalizacao", VariableType.REQUEST_PARAM));

		String pedidoUrl = linkTo(PedidoController.class).toUri().toString();

		return new Link(UriTemplate.of(pedidoUrl, pagesVariables.concat(filtroVariable)), "pedidos");

	}

}
