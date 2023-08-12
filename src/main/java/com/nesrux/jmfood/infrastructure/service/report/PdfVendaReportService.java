package com.nesrux.jmfood.infrastructure.service.report;

import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.filter.VendaDiariaFilter;
import com.nesrux.jmfood.domain.service.VendaReportService;

@Service
public class PdfVendaReportService implements VendaReportService {

	//@Autowired
	//private VendaQueryService vendaService;

	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
	/**	try {
			var inputStream = this.getClass().getResourceAsStream(
					"/relatorios/jmfoodReport.jasper");
			
			var parametros = new HashMap<String, Object>();
			parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
			
			var vendasDiarias = vendaService.consultarVendaDiarias(filtro, timeOffset);
			var dataSource = new JRBeanCollectionDataSource(vendasDiarias);
			
			var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
		
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new ReportException("Não foi possível emitir relatório de vendas diárias", e);
		}**/
		return null;
	}
}
