package com.nesrux.jmfood.infrastructure.service.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.nesrux.jmfood.domain.service.FotoStorafeService;
import com.nesrux.jmfood.infrastructure.exception.StrorageException;

@Service
public class FotoStorageService implements FotoStorafeService {
	@Value("{jmfood.storage.local.diretorioFotos}")
	private Path diretorioFotos;

	@Override
	public void armazenar(NovaFoto novafoto) {
		Path arquivoPath = getArquivoPath(novafoto.getNomeArquivo());

		try {
			FileCopyUtils.copy(novafoto.getInputStream(), Files.newOutputStream(arquivoPath));
		} catch (Exception e) {
			throw new StrorageException("Não foi possivel armazenar a foto no disco Local", e);
		}
	}

	private Path getArquivoPath(String Nomearquivo) {
		return diretorioFotos.resolve(Path.of(Nomearquivo));
	}

}
