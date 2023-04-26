package com.nesrux.jmfood.api.exceptionHandler;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErroApi {

	private LocalDateTime datahora;
	private String mensagem;
}
