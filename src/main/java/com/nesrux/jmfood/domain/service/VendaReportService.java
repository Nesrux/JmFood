package com.nesrux.jmfood.domain.service;

import com.nesrux.jmfood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

	byte[] emitirVendasDiarias(VendaDiariaFilter filtar, String timeOffset);
}
