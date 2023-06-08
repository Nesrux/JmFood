package com.nesrux.jmfood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public interface FotoStorageService {
	void armazenar(NovaFoto novafoto);

	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;

	}

	@Getter
	@Setter
	@Builder
	class NovaFoto {
		private String nomeArquivo;
		private InputStream inputStream;

	}
}
