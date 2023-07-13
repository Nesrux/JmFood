package com.nesrux.jmfood.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.v1.openapi.controller.estatisticas.EstatisticasControllerOpenApi;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.domain.filter.VendaDiariaFilter;
import com.nesrux.jmfood.domain.model.dto.VendaDiaria;
import com.nesrux.jmfood.domain.service.VendaQueryService;
import com.nesrux.jmfood.domain.service.VendaReportService;

@RestController
@RequestMapping(path = "/v1/estatisticas")
public class EstatisticasController implements EstatisticasControllerOpenApi {
	@Autowired
	private VendaQueryService vendaQueryService;
	@Autowired
	private VendaReportService reportService;
	@Autowired
	private JmFoodLinks links;

	@GetMapping
	public Estatisticas estatisticasLinks() {
		Estatisticas estatistica = new Estatisticas();
		estatistica.add(links.linkToVendasDiarias("venda-diaria"));

		return estatistica;
	}

	@Override
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
		return vendaQueryService.consultarVendaDiarias(filtro, timeOffset);
	}

	@Override
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {

		byte[] bytesPdf = reportService.emitirVendasDiarias(filtro, timeOffset);

		var header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=elmaMariaPinto.pdf");// HAHA comédias

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).headers(header).body(bytesPdf);
	}
	public static class Estatisticas extends RepresentationModel<Estatisticas> {}

}
