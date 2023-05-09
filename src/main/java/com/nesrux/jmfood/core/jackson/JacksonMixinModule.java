package com.nesrux.jmfood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.nesrux.jmfood.api.model.mixin.CidadeMixin;
import com.nesrux.jmfood.api.model.mixin.ProdutoMixin;
import com.nesrux.jmfood.api.model.mixin.RestauranteMixin;
import com.nesrux.jmfood.domain.model.endereco.Cidade;
import com.nesrux.jmfood.domain.model.pedido.Produto;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;

@Component
public class JacksonMixinModule extends SimpleModule {
	private static final long serialVersionUID = 1L;

	/**
	 * Essa classe é um classe de config do jackson, ela é usada para fazer a
	 * "mixisagem" entre a calsse Restaurantemixin e a classe Restaurante do domain
	 * model, na hora da serialização ou descerialização do objeto ela vai utilizar
	 * as anotações que estão no mmixin e atribuir a classe que esta no model assim,
	 * a classe de modelo fica mais "limpa", e tira as anotações que só são
	 * pertinentes a API e não ao domin model como por exemplo as anotações de JPA,
	 * validation ou lombok que são pernitentes apenas à classe de dominio
	 */
	
	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
		setMixInAnnotation(Produto.class, ProdutoMixin.class);
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
	}
}
