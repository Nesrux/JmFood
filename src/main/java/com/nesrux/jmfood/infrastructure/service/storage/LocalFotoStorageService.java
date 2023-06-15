package com.nesrux.jmfood.infrastructure.service.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.nesrux.jmfood.core.storage.StorageProperties;
import com.nesrux.jmfood.domain.service.FotoStorageService;
import com.nesrux.jmfood.infrastructure.exception.StorageException;


public class LocalFotoStorageService implements FotoStorageService {
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void armazenar(NovaFoto novafoto) {
		Path arquivoPath = getArquivoPath(novafoto.getNomeArquivo());

		try {
			FileCopyUtils.copy(novafoto.getInputStream(), Files.newOutputStream(arquivoPath));
		} catch (Exception e) {
			throw new StorageException("Não foi possivel armazenar a foto no disco Local", e);
		}
	}

	@Override
	public void remover(String NomeFotoAntiga) {
		try {
			Path arquivoPath = getArquivoPath(NomeFotoAntiga);
			Files.deleteIfExists(arquivoPath);
		} catch (Exception e) {
			throw new StorageException("não foi possivel excluir arquivo", e);
		}
	}

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		Path arquivoPath = getArquivoPath(nomeArquivo);
		try {
			FotoRecuperada fotoRecuperada = FotoRecuperada.builder()
					.inputStream(Files.newInputStream(arquivoPath))
					.build();
			return fotoRecuperada;
		} catch (Exception e) {
			throw new StorageException("não foi possivel recuperar o arquivo", e);

		}
	}

	private Path getArquivoPath(String Nomearquivo) {
		return storageProperties.getLocal().getDiretorioFotos().resolve(Path.of(Nomearquivo));
	}

}
