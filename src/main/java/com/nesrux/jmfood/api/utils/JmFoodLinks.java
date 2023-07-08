package com.nesrux.jmfood.api.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.controller.CidadeController;
import com.nesrux.jmfood.api.controller.CozinhaController;
import com.nesrux.jmfood.api.controller.EstadoController;
import com.nesrux.jmfood.api.controller.FormaPagamentoController;
import com.nesrux.jmfood.api.controller.PedidoController;
import com.nesrux.jmfood.api.controller.RestauranteController;
import com.nesrux.jmfood.api.controller.UsuarioController;
import com.nesrux.jmfood.api.controller.subcontrollers.pedidos.FluxoPedidoController;
import com.nesrux.jmfood.api.controller.subcontrollers.restaurantes.RestauranteFormaPagamentoController;
import com.nesrux.jmfood.api.controller.subcontrollers.restaurantes.RestauranteProdutoController;
import com.nesrux.jmfood.api.controller.subcontrollers.restaurantes.RestauranteUsuarioController;
import com.nesrux.jmfood.api.controller.subcontrollers.usuarios.UsuarioGrupoController;

@Component
public class JmFoodLinks {
	public static final TemplateVariables pagesVariables = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));

	public Link linkToPedidos(String rel) {
		TemplateVariables filtroVariable = new TemplateVariables(
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacao", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataFinalizacao", VariableType.REQUEST_PARAM));

		String pedidoUrl = linkTo(PedidoController.class).toUri().toString();

		return new Link(UriTemplate.of(pedidoUrl, pagesVariables.concat(filtroVariable)), rel);

	}

	public Link linkToRestaurante(String rel) {
		TemplateVariables projecaoRestaurante = new TemplateVariables(
				new TemplateVariable("projecao=apenas-nome", VariableType.REQUEST_PARAM));

		String ListagemRestaurante = linkTo(RestauranteController.class).toUri().toString();
		return new Link(UriTemplate.of(ListagemRestaurante, projecaoRestaurante), rel);
	}

	// Listagem de usuarios
	public Link linktoUsuario(String rel) {
		return linkTo((UsuarioController.class)).withRel(rel);
	}

	// Listagem de pedidos
	public Link linkToPedidos(Long RestauranteId, Long produtoId, String rel) {
		return linkTo(PedidoController.class).withRel(rel);
	}

	// Listagem de cidade
	public Link linkToCidade(String rel) {
		return linkTo(CidadeController.class).withRel(rel);
	}

	// Listagem de Estados
	public Link linkToEstado(String rel) {
		return linkTo(EstadoController.class).withRel(rel);
	}

	// Listagem de grupos de um usuario
	public Link linkToGrupoUsuario(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).listarGruposUsuario(usuarioId)).withRel(rel);
	}

	// Listagem de cozinhas
	public Link linkToCozinha(String rel) {
		return linkTo((CozinhaController.class)).withRel(rel);
	}

	public Link linkToUsuarioResponsavel(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteUsuarioController.class).listarFuncionariosRestaurante(restauranteId))
				.withRel(rel);
	}

	public Link linkToConfirmarPedido(String codigoPedido, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).confirmarPedido(codigoPedido)).withRel(rel);
	}

	public Link linkToEntregaPedido(String codigoPedido, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).entregarPedido(codigoPedido)).withRel(rel);
	}

	public Link linkToCancelarPedido(String codigoPedido, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).cancelarPedido(codigoPedido)).withRel(rel);
	}

	// Link forma-pagamento de um restaurnte
	public Link linkToFormaPagamentoRestaurante(Long restauranteid, String rel) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class).listar(restauranteid)).withRel(rel);
	}

	// ================================== SELF
	// ===============================================
	// Link cliente
	public Link linkToCliente(Long clienteId) {
		return linkTo((methodOn(UsuarioController.class).buscar(clienteId))).withSelfRel();
	}

	// Link forma-pagamento
	public Link linkToFormaPagamento(Long formaPagamentoId) {
		return linkTo(methodOn(FormaPagamentoController.class).buscar(formaPagamentoId, null)).withSelfRel();
	}

	// Link cidade
	public Link linkToCidade(Long cidadeId) {
		return linkTo(methodOn(CidadeController.class).buscar(cidadeId)).withSelfRel();
	}

	// Link para Estado
	public Link linkToEstado(Long estadoId) {
		return linkTo(methodOn(EstadoController.class).buscar(estadoId)).withSelfRel();
	}

	// Link para cozinha
	public Link linkToCozinha(Long cozinhaId) {
		return linkTo(methodOn(CozinhaController.class).buscar(cozinhaId)).withSelfRel();
	}

	// Link para Restaurante
	public Link linkToRestaurante(Long restauranteId) {
		return linkTo(methodOn(RestauranteController.class).buscar(restauranteId)).withSelfRel();
	}

	// Link para produtos
	public Link linkToProduto(Long RestauranteId, Long produtoId) {
		return linkTo(methodOn(RestauranteProdutoController.class).buscar(RestauranteId, produtoId)).withSelfRel();
	}

	// Link para usuario
	public Link linktoUsuario(Long usuarioId) {
		return linkTo(methodOn(UsuarioController.class).buscar(usuarioId)).withSelfRel();
	}

}
