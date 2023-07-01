package com.nesrux.jmfood.api.springFox.controller.estatisticas;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nesrux.jmfood.domain.filter.VendaDiariaFilter;
import com.nesrux.jmfood.domain.model.dto.VendaDiaria;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Estatisticas")
public interface EstatisticasControllerOpenApi {

	@ApiOperation("Consulta de vendas diarias")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "restauranteId", value = "Id de um restaurante", example = "1", type = "int"),
		@ApiImplicitParam(name = "dataCriacao", value = "Data inicial da amostragem dos dados", example = "2023-04-01T00:00:00Z", type = "date-time"),
		@ApiImplicitParam(name = "dataFinalizacao", value = "Data final da amostragem dos dados", example = "2023-07-01T00:00:00Z", type = "date-time")
	})
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
			@ApiParam(value = "Deslocamento de horário deve ser considerado na consulta em relação a UTC", defaultValue = "+00:00") String timeOffset);

	
	
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);

}
