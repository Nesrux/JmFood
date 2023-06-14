package com.nesrux.jmfood.core.storage;

import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("jmfood.storage")
@Getter
@Setter
public class StorageProperties {

	private Local local = new Local();

	@Getter
	@Setter
	public class Local {
	//	private Path diretorioFotos = Path.of("C:\\Users\\jucaj\\OneDrive\\Área de Trabalho\\BANCO_DE_FOTOS");
		private Path diretorioFotos;
	}

	// TODO por causa de erro de compatibilidade usar o @configurationProperties
	// esta dando erro, então quando atualizar o projeto mudar isso aqui
}
