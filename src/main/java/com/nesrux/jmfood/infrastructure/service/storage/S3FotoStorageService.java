package com.nesrux.jmfood.infrastructure.service.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nesrux.jmfood.core.storage.StorageProperties;
import com.nesrux.jmfood.domain.service.FotoStorageService;
import com.nesrux.jmfood.infrastructure.exception.StorageException;

//@Service
public class S3FotoStorageService implements FotoStorageService {

	@Autowired
	private AmazonS3 amazonS3;

	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());

			var objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(novaFoto.getContentType());

			var putObjectRequest = new PutObjectRequest(storageProperties.getS3().getBucket(), caminhoArquivo,
					novaFoto.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead);

			amazonS3.putObject(putObjectRequest);
		} catch (Exception e) {
			throw new StorageException("não foi possivel enviar arquivo para Amazon S3", e);
		}
	}

	@Override
	public void remover(String NomeFotoAntiga) {
		String caminhoArquivo = getCaminhoArquivo(NomeFotoAntiga);
		try {
			var deleteObjetRequest = new DeleteObjectRequest(storageProperties.getS3().getBucket(), caminhoArquivo);

			amazonS3.deleteObject(deleteObjetRequest);
		} catch (Exception e) {
			throw new StorageException("não foi possivel apagar arquivo para Amazon S3", e);
		}
	}

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
		URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(),
				caminhoArquivo);
		
		return FotoRecuperada.builder()
				.url(url.toString())
				.build();
	}

	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
	}
}
