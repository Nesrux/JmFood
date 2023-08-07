package com.nesrux.jmfood.api.v1.controller.subcontrollers.pedidos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.v1.openapi.controller.pedido.FluxoPedidoControllerOpenApi;
import com.nesrux.jmfood.core.security.anotations.CheckSecurity;
import com.nesrux.jmfood.domain.service.FluxoPedidoService;

@RestController
@RequestMapping(path = "/v1/pedidos/{codigoPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
public class FluxoPedidoController implements FluxoPedidoControllerOpenApi {
	@Autowired
	private FluxoPedidoService fluxoService;

	@Override
	@PutMapping("/confirmar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.Pedidos.podeGerenciar
	public ResponseEntity<Void> confirmarPedido(@PathVariable String codigoPedido) {
		fluxoService.confirmar(codigoPedido);

		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/entregar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.Pedidos.podeGerenciar
	public ResponseEntity<Void> entregarPedido(@PathVariable String codigoPedido) {
		fluxoService.entregar(codigoPedido);
		return ResponseEntity.noContent().build();

	}

	@Override
	@PutMapping("/cancelar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.Pedidos.podeGerenciar
	public ResponseEntity<Void> cancelarPedido(@PathVariable String codigoPedido) {
		fluxoService.cancelar(codigoPedido);
		return ResponseEntity.noContent().build();

	}
}
