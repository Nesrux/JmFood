package com.nesrux.jmfood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public interface FotoStorageService {
	void armazenar(NovaFoto novafoto);

	void remover(String NomeFotoAntiga);

	FotoRecuperada recuperar(String nomeArquivo);

	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;

	}

	default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
		this.armazenar(novaFoto);

		if (nomeArquivoAntigo != null) {
			this.remover(nomeArquivoAntigo);
		}
	}

	@Getter
	@Setter
	@Builder
	class NovaFoto {
		private String nomeArquivo;
		private String contentType;
		private InputStream inputStream;

	}

	@Getter
	@Builder
	class FotoRecuperada {
		private InputStream inputStream;
		private String url;

		public boolean temUrl() { 
			return url != null;
		}
		public boolean temInputStream() {
			return inputStream != null;
		}
	}
}
