package com.nesrux.jmfood.api.v1.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.controller.CidadeController;
import com.nesrux.jmfood.api.v1.controller.CozinhaController;
import com.nesrux.jmfood.api.v1.controller.EstadoController;
import com.nesrux.jmfood.api.v1.controller.EstatisticasController;
import com.nesrux.jmfood.api.v1.controller.FormaPagamentoController;
import com.nesrux.jmfood.api.v1.controller.GrupoController;
import com.nesrux.jmfood.api.v1.controller.PedidoController;
import com.nesrux.jmfood.api.v1.controller.PermissaoController;
import com.nesrux.jmfood.api.v1.controller.RestauranteController;
import com.nesrux.jmfood.api.v1.controller.UsuarioController;
import com.nesrux.jmfood.api.v1.controller.subcontrollers.grupo.GrupoPermissaoController;
import com.nesrux.jmfood.api.v1.controller.subcontrollers.pedidos.FluxoPedidoController;
import com.nesrux.jmfood.api.v1.controller.subcontrollers.restaurantes.RestauranteFormaPagamentoController;
import com.nesrux.jmfood.api.v1.controller.subcontrollers.restaurantes.RestauranteFotoProdutoController;
import com.nesrux.jmfood.api.v1.controller.subcontrollers.restaurantes.RestauranteProdutoController;
import com.nesrux.jmfood.api.v1.controller.subcontrollers.restaurantes.RestauranteUsuarioController;
import com.nesrux.jmfood.api.v1.controller.subcontrollers.usuarios.UsuarioGrupoController;

@Component
public class JmFoodLinks {
	public static final TemplateVariables pagesVariables = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));

	public static final TemplateVariables PROJECAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("projecao", VariableType.REQUEST_PARAM));

	public static final TemplateVariables QUERYVENDADIARIA = new TemplateVariables(
			new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
			new TemplateVariable("dataCriacao", VariableType.REQUEST_PARAM),
			new TemplateVariable("dataFinalizacao", VariableType.REQUEST_PARAM));

	public Link linkToConfirmacaoPedido(String codigoPedido, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).confirmarPedido(codigoPedido)).withRel(rel);
	}

	public Link linkToEntregaPedido(String codigoPedido, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).entregarPedido(codigoPedido)).withRel(rel);
	}

	public Link linkToCancelamentoPedido(String codigoPedido, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).cancelarPedido(codigoPedido)).withRel(rel);
	}

	public Link linkToRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).buscar(restauranteId)).withRel(rel);
	}

	public Link linkToRestaurante(Long restauranteId) {
		return linkToRestaurante(restauranteId, IanaLinkRelations.SELF.value());
	}

	public Link linkToRestaurantes(String rel) {
		String restaurantesUrl = linkTo(RestauranteController.class).toUri().toString();

		return new Link(UriTemplate.of(restaurantesUrl, PROJECAO_VARIABLES), rel);
	}

	public Link linkToRestaurantes() {
		return linkToRestaurantes(IanaLinkRelations.SELF.value());
	}

	public Link linkToRestauranteFormasPagamento(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class).listar(restauranteId)).withRel(rel);
	}

	public Link linkToRestauranteFormasPagamento(Long restauranteId) {
		return linkToRestauranteFormasPagamento(restauranteId, IanaLinkRelations.SELF.value());
	}

	public Link linktoRestauranteFormaPagamentoDesassociacao(Long restauranteid, Long formaPagamentoId, String rel) {

		return linkTo(methodOn(RestauranteFormaPagamentoController.class).desassociarFormaPagamento(restauranteid,
				formaPagamentoId)).withRel(rel);
	}

	public Link linktoRestauranteFormaPagamentoDesassociacao(Long restauranteid, Long formaPagamentoId) {

		return linktoRestauranteFormaPagamentoDesassociacao(restauranteid, formaPagamentoId,
				IanaLinkRelations.SELF.value());
	}

	public Link linktoRestauranteFormaPagamentoAssociar(Long restauranteid, String rel) {

		return linkTo(methodOn(RestauranteFormaPagamentoController.class).associarFormaPagamento(restauranteid, null))
				.withRel(rel);
	}

	public Link linkToRestauranteAbertura(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).abrirRestaurante(restauranteId)).withRel(rel);
	}

	public Link linkToRestauranteFechamento(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).fecharRestaurante(restauranteId)).withRel(rel);
	}

	public Link linkToRestauranteInativacao(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).desativarRestaurante(restauranteId)).withRel(rel);
	}

	public Link linkToRestauranteAtivacao(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).ativarRestaurante(restauranteId)).withRel(rel);
	}

	public Link linkToGrupo(Long grupoId, String rel) {
		return linkTo(methodOn(GrupoController.class).buscar(grupoId)).withRel(rel);
	}

	public Link linkToGrupo(Long grupoId) {
		return linkToGrupo(grupoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToGrupos(String rel) {
		return linkTo(methodOn(GrupoController.class).listar()).withRel(rel);
	}

	public Link linkToGrupos() {
		return linkToGrupos(IanaLinkRelations.SELF.value());
	}

	public Link linkToAssociarGrupo(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).associarGrupo(usuarioId, null)).withRel(rel);
	}

	public Link linkToDesassociargrupo(Long grupoId, Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).desassociarGrupo(usuarioId, grupoId)).withRel(rel);
	}

	public Link linkToPermissoes(String rel) {
		return linkTo(methodOn(PermissaoController.class).listarPermissoes()).withRel(rel);
	}

	public Link linkToPermissoes() {
		return linkToPermissoes(IanaLinkRelations.SELF.value());
	}

	public Link linkToAssociarPermissão(Long grupoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).associarPermissao(grupoId, null)).withRel(rel);
	}

	public Link linktoDesassociarPermissao(Long grupoId, Long permissaoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).dessassociarPermissao(grupoId, permissaoId))
				.withRel(rel);
	}

	public Link linkToGrupoPermissao(Long grupoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).ListarPermissoes(grupoId)).withRel(rel);
	}

	public Link linkToGrupoPermissao(Long grupoId) {
		return linkToGrupoPermissao(grupoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToUsuario(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioController.class).buscar(usuarioId)).withRel(rel);
	}

	public Link linkToUsuario(Long usuarioId) {
		return linkToUsuario(usuarioId, IanaLinkRelations.SELF.value());
	}

	public Link linkToUsuarios(String rel) {
		return linkTo(UsuarioController.class).withRel(rel);
	}

	public Link linkToUsuarios() {
		return linkToUsuarios(IanaLinkRelations.SELF.value());
	}

	public Link linkToGruposUsuario(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).listarGruposUsuario(usuarioId)).withRel(rel);
	}

	public Link linkToGruposUsuario(Long usuarioId) {
		return linkToGruposUsuario(usuarioId, IanaLinkRelations.SELF.value());
	}

	public Link linkToRestauranteResponsaveis(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteUsuarioController.class).listarFuncionariosRestaurante(restauranteId))
				.withRel(rel);
	}

	public Link linkToRestauranteResponsaveis(Long restauranteId) {
		return linkToRestauranteResponsaveis(restauranteId, IanaLinkRelations.SELF.value());
	}

	public Link linkToUsuarioDesassociar(Long restauranteId, Long usuarioId, String rel) {
		return linkTo(methodOn(RestauranteUsuarioController.class).desassociarFuncionarios(restauranteId, usuarioId))
				.withRel(rel);
	}

	public Link linkToUsuarioDesassociar(Long restauranteId, Long usuarioId) {
		return linkToUsuarioDesassociar(restauranteId, usuarioId, IanaLinkRelations.SELF.value());
	}

	public Link linkToUsuarioAssociar(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteUsuarioController.class).associarFuncionarios(restauranteId, null))
				.withRel(rel);
	}

	public Link linkToFormaPagamento(Long formaPagamentoId, String rel) {
		return linkTo(methodOn(FormaPagamentoController.class).buscar(formaPagamentoId, null)).withRel(rel);
	}

	public Link linkToFormaPagamento(Long formaPagamentoId) {
		return linkToFormaPagamento(formaPagamentoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToCidade(Long cidadeId, String rel) {
		return linkTo(methodOn(CidadeController.class).buscar(cidadeId)).withRel(rel);
	}

	public Link linkToCidade(Long cidadeId) {
		return linkToCidade(cidadeId, IanaLinkRelations.SELF.value());
	}

	public Link linkToCidades(String rel) {
		return linkTo(CidadeController.class).withRel(rel);
	}

	public Link linkToCidades() {
		return linkToCidades(IanaLinkRelations.SELF.value());
	}

	public Link linkToEstado(Long estadoId, String rel) {
		return linkTo(methodOn(EstadoController.class).buscar(estadoId)).withRel(rel);
	}

	public Link linkToEstado(Long estadoId) {
		return linkToEstado(estadoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToEstados(String rel) {
		return linkTo(EstadoController.class).withRel(rel);
	}

	public Link linkToEstados() {
		return linkToEstados(IanaLinkRelations.SELF.value());
	}

	public Link linkToProduto(Long restauranteId, Long produtoId, String rel) {
		return linkTo(methodOn(RestauranteProdutoController.class).buscar(restauranteId, produtoId)).withRel(rel);
	}

	public Link linkToProduto(Long restauranteId, Long produtoId) {
		return linkToProduto(restauranteId, produtoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToProdutos(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteProdutoController.class).listar(restauranteId, null)).withRel(rel);
	}

	public Link linkToProdutos(Long restauranteId) {
		return linkToProdutos(restauranteId, IanaLinkRelations.SELF.value());
	}

	public Link linkToFotoProduto(Long restauranteId, Long produtoId, String rel) {
		return linkTo(methodOn(RestauranteFotoProdutoController.class).buscarFoto(restauranteId, produtoId))
				.withRel(rel);
	}

	public Link linkToFotoProduto(Long restauranteId, Long produtoId) {
		return linkToFotoProduto(restauranteId, produtoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToCozinhas(String rel) {
		return linkTo(CozinhaController.class).withRel(rel);
	}

	public Link linkToCozinhas() {
		return linkToCozinhas(IanaLinkRelations.SELF.value());
	}

	public Link linkToCozinha(Long cozinhaId, String rel) {
		return linkTo(methodOn(CozinhaController.class).buscar(cozinhaId)).withRel(rel);
	}

	public Link linkToCozinha(Long cozinhaId) {
		return linkToCozinha(cozinhaId, IanaLinkRelations.SELF.value());
	}

	public Link linkToPedido(String codigo, String rel) {
		return linkTo(methodOn(PedidoController.class).buscar(codigo)).withRel(rel);
	}

	public Link linkToPedido(String codigo) {
		return linkToPedido(codigo, IanaLinkRelations.SELF.value());
	}

	public Link linkToPedidos(String rel) {
		return linkTo(methodOn(PedidoController.class).pesquisarPedidos(null, null)).withRel(rel);
	}

	public Link linkToPedidos() {
		return linkToPedidos(IanaLinkRelations.SELF.value());
	}

	public Link linkToFormasPagamentos(String rel) {
		return linkTo(methodOn(FormaPagamentoController.class).listar(null)).withRel(rel);
	}

	public Link linkToFormasPagamentos() {
		return linkToFormasPagamentos(IanaLinkRelations.SELF.value());
	}

	public Link linkToVendasDiarias(String rel) {
		String uriEstatisticas = linkTo(EstatisticasController.class).toUri().toString();
		return new Link(UriTemplate.of(uriEstatisticas, QUERYVENDADIARIA), rel);
	
	}

	public Link linkToEstatisticas(String rel) {
		return linkTo(methodOn(EstatisticasController.class).estatisticasLinks()).withRel(rel);
	}

}
