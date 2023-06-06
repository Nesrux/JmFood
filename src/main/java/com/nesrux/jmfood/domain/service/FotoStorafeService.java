package com.nesrux.jmfood.domain.service;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public interface FotoStorafeService {
	void armazenar(NovaFoto novafoto);

	@Getter
	@Setter
	@Builder
	class NovaFoto {
		private String nomeArquivo;
		private InputStream inputStream;

	}
}
