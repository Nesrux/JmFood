package com.nesrux.jmfood.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum TipoProblema {
	ENTIDADE_NAO_ENCONTRADA("entidade-nao-encontrada",
			"Entidade nao encontrada"), ENTIDADE_EM_USO("entidade-em-uso",
					"Entidade esta em uso");

	private String titulo;
	private String uri;

	private TipoProblema(String caminho, String titulo) {
		this.uri = "https://jmfood.com.br/" + caminho;
		this.titulo = titulo;

	}

	/*
	 * Enum que ajuda no builder de ErroAPI, para facilitar a escrita de novos
	 * códigos e na manutenção do mesmo.
	 */
}
