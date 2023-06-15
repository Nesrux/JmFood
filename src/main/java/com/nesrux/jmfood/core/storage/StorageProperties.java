package com.nesrux.jmfood.core.storage;

import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("jmfood.storage")
@Getter
@Setter
public class StorageProperties {

	private Local local = new Local();
	private S3 s3 = new S3();

	@Getter
	@Setter
	public class Local {
		// private Path diretorioFotos = Path.of("C:\\Users\\jucaj\\OneDrive\\Área de
		// Trabalho\\BANCO_DE_FOTOS");
		private Path diretorioFotos;
	}

	@Getter
	@Setter
	public class S3 {
		private String idChaveAcesso;
		private String chaveAcessoSecreta;
		private String bucket;
		private Regions regiao;
		private String diretorioFotos;
	}

	// TODO por causa de erro de compatibilidade usar o @configurationProperties
	// esta dando erro, então quando atualizar o projeto mudar isso aqui
}
