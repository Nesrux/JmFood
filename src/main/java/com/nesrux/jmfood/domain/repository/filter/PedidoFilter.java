package com.nesrux.jmfood.domain.repository.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoFilter {

	private Long clienteId;
	private Long restauranteId;
	// Força a conversão de String para dateTime na hora da instanciação do
	// controller
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacao;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataFinalizacao;

}
