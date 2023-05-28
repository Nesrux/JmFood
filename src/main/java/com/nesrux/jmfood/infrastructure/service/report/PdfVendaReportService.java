package com.nesrux.jmfood.infrastructure.service.report;

import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.filter.VendaDiariaFilter;
import com.nesrux.jmfood.domain.service.VendaReportService;

@Service
public class PdfVendaReportService implements VendaReportService{

	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filtar, String timeOffset) {
		return null;
	}

}
