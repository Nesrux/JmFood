package com.nesrux.jmfood.infrastructure.service.storage;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.service.FotoStorageService;

@Service
public class S3FotoStorageService implements FotoStorageService {

	@Override
	public void armazenar(NovaFoto novafoto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(String NomeFotoAntiga) {
		// TODO Auto-generated method stub

	}

	@Override
	public InputStream recuperar(String nomeArquivo) {
		// TODO Auto-generated method stub
		return null;
	}

}
