package com.nesrux.jmfood.infrastructure.service.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.nesrux.jmfood.domain.service.FotoStorageService;
import com.nesrux.jmfood.infrastructure.exception.StrorageException;

@Service
public class LocalFotoStorageService implements FotoStorageService {
	//TODO Injetar uma propriedade do application.properties com @value
	//nessa versão do isso não esta funcionando
	//dps a atualização apagar tudo oque vem deps do  = 
	// e colocar um @Value("{jmfood.storage.local.diretorio-fotos}");
	private Path diretorioFotosPath = Path.of("C:\\Users\\jucaj\\OneDrive\\Área de Trabalho\\BANCO_DE_FOTOS");

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
		return diretorioFotosPath.resolve(Path.of(Nomearquivo));
	}

}
