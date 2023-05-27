package com.nesrux.jmfood.domain.filter;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDiariaFilter {

	private Long restauranteId;
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataFinalizacao;
}
