package com.nesrux.jmfood.api.controller.subcontrollers.restaurantes;

import java.nio.file.Path;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteFotoProdutoController {

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestParam MultipartFile arquivo) {

		var nomeArquivo = UUID.randomUUID().toString() + "_" + arquivo.getOriginalFilename();

		var arquivoFoto = Path.of(
				"C:\\Users\\jucaj\\OneDrive\\Área de Trabalho\\Programacao\\Spring\\JmFood\\BANCO_DE_FOTOS",
				nomeArquivo);
		System.out.println(arquivoFoto);
		System.out.println(arquivo.getContentType());
		
		try {
			arquivo.transferTo(arquivoFoto);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
