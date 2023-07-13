package com.nesrux.jmfood.api.v1.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nesrux.jmfood.domain.model.endereco.Estado;

public class CidadeMixin {
	
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Estado estado;
}
