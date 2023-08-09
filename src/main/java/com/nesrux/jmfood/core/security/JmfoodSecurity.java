package com.nesrux.jmfood.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.repository.PedidoRepository;
import com.nesrux.jmfood.domain.repository.RestauranteRepository;

@Component
public class JmfoodSecurity {
	@Autowired
	private RestauranteRepository restauranteRepository;

	private PedidoRepository pedidoRepository;

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean isAutenticado() {
		return getAuthentication().isAuthenticated();
	}

	public Long getUsuarioId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();

		return jwt.getClaim("usuario_id");
	}

	public boolean gerenciaRestaurante(Long restauranteId) {
		return restauranteRepository.existsResponsavel(restauranteId, getUsuarioId());
	}

	public boolean gerenciaPedido(String codigoPedido) {
		return pedidoRepository.isPedidoGerenciadoPor(codigoPedido, getUsuarioId());
	}

	public boolean usuarioAuthenticadoIgual(Long usuarioId) {
		return getUsuarioId() != null && usuarioId != null && getUsuarioId().equals(usuarioId);
	}

	public boolean hasAuthority(String authorityName) {
		return getAuthentication().getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals(authorityName));
	}

	public boolean podeGerenciarPedidos(String codigoPedido) {
		return hasAuthority("SCOPE_WRITE") && hasAuthority("GERENCIAR_PEDIDOS") || gerenciaPedido(codigoPedido);
	}

	public boolean temEscopoEscrita() {
		return hasAuthority("SCOPE_WRITE");
	}

	public boolean temEscopoLeitura() {
		return hasAuthority("SCOPE_READ");
	}

	public boolean podePesquisarPedidos() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarFormasPagamento() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarCidades() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarEstados() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarCozinhas() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarEstatisticas() {
		return temEscopoLeitura() && hasAuthority("GERAR_RELATORIOS");
	}

	public boolean podeConsultarUsuariosGruposPermissoes() {
		return temEscopoLeitura() && hasAuthority("CONSULTAR_USUARIOS_GRUPOS_PERMISSOES");
	}

	public boolean podeEditarUsuariosGruposPermissoes() {
		return temEscopoEscrita() && hasAuthority("EDITAR_USUARIOS_GRUPOS_PERMISSOES");
	}

}
