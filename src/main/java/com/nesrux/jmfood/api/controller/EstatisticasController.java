package com.nesrux.jmfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.domain.filter.VendaDiariaFilter;
import com.nesrux.jmfood.domain.model.dto.VendaDiaria;
import com.nesrux.jmfood.domain.service.VendaQueryService;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {
	@Autowired
	private VendaQueryService vendaQueryService;

	@GetMapping("/vendas-diarias")
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
		return vendaQueryService.consultarVendaDiarias(filtro);
	}

}
