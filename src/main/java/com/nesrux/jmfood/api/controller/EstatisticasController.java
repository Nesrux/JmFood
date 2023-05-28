package com.nesrux.jmfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.domain.filter.VendaDiariaFilter;
import com.nesrux.jmfood.domain.model.dto.VendaDiaria;
import com.nesrux.jmfood.domain.service.VendaQueryService;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {
	@Autowired
	private VendaQueryService vendaQueryService;

	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
		return vendaQueryService.consultarVendaDiarias(filtro, timeOffset);
	}
	
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
	public List<VendaDiaria> consultarVendasDiariasPdf(VendaDiariaFilter filtro,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
		return null;
	}


}
