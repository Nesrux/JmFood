package com.nesrux.jmfood.domain.service;

import java.util.List;

import com.nesrux.jmfood.domain.filter.VendaDiariaFilter;
import com.nesrux.jmfood.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendaDiarias(VendaDiariaFilter filtro, String timeOfSet);
}
