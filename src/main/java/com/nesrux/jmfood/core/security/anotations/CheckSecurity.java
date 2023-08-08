package com.nesrux.jmfood.core.security.anotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Cozinhas {
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {}

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}

	}

	public @interface restaurantes {
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_RESTAURANTES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface podeGerenciarCadastro {}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and " + "(hasAuthority('EDITAR_RESTAURANTES') or "
				+ "@jmfoodSecurity.gerenciaRestaurante(#restauranteId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarFuncionamento {}

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}

	}

	public @interface Pedidos {

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or "
				+ "@jmfoodSecurity.getUsuarioId() == returnObject.cliente.id or"
				+ " @jmfoodSecurity.gerenciaRestaurante(returnObject.restaurante.restauranteId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeBuscar {}

		//Obs : para pegar os dados da classe utilizando o # tem que ser o nome da variavel e não o nome da bean 
		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('CONSULTAR_PEDIDOS') or "
				+ "@jmfoodSecurity.getUsuarioId() == #filter.clienteId or"
				+ "@jmfoodSecurity.gerenciaRestaurante(#filter.restauranteId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodePesquisar {}
	
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('GERENCIAR_PEDIDOS') or "
				+ "@jmfoodSecurity.gerenciaPedido(#codigoPedido)")	
		@Retention(RUNTIME)
		@Target(METHOD)		
		public @interface podeGerenciar{}
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface podeCriar{}

	}
	
	public @interface FormasPagamento{
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface podeConsultar{}
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface podeEditar{}
	}
	
	public @interface Estados{
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface podeConsultar{}
	
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ESTADOS')")
		@Retention(RUNTIME)
		@Target(METHOD)	
		public @interface podeEditar{}
	}
	
	public @interface Cidades{
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface podeConsultar{}
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CIDADES')")
		@Retention(RUNTIME)
		@Target(METHOD)	
		public @interface podeEditar{}
	
	}
	
	public @interface Estatisticas{
		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('GERAR_RELATORIOS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface podeConsultar{}
		
	}

}
