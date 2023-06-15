package com.nesrux.jmfood.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.nesrux.jmfood.core.storage.StorageProperties.TipoStorage;
import com.nesrux.jmfood.domain.service.FotoStorageService;
import com.nesrux.jmfood.infrastructure.service.storage.LocalFotoStorageService;
import com.nesrux.jmfood.infrastructure.service.storage.S3FotoStorageService;

@Configuration
public class StorageConfig {
	@Autowired
	private StorageProperties storageProperties;

	@Bean
	public AmazonS3 amazons3() {
		var credenciais = new BasicAWSCredentials(storageProperties.getS3().getIdChaveAcesso(),
				storageProperties.getS3().getChaveAcessoSecreta());

		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credenciais))
				.withRegion(storageProperties.getS3().getRegiao()).build();

	}

	@Bean
	public FotoStorageService fotoStorageService() {
		if (TipoStorage.S3.equals(storageProperties.getTipo())) {
			return new S3FotoStorageService();
		} else {
			return new LocalFotoStorageService();
		}
		// Verifica qual implementação de armazenamento que o user quer, e depois cria
		// nova instancia dela
	};
}
