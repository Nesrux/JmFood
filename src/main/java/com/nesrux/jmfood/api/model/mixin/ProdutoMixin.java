package com.nesrux.jmfood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;

public class ProdutoMixin {
	
	@JsonIgnore
	private Restaurante restaurante;

}
