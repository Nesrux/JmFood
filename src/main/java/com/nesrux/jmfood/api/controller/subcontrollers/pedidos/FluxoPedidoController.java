package com.nesrux.jmfood.api.controller.subcontrollers.pedidos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.domain.service.FluxoPedidoService;

@RestController
@RequestMapping("/pedidos/{pedidoId}")
public class FluxoPedidoController {
	@Autowired
	private FluxoPedidoService fluxoService;
	@PutMapping("/confirmar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmarPedido(@PathVariable Long pedidoId) {
		fluxoService.confirmar(pedidoId);
	}

	@PutMapping("/entregar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entregarPedido(@PathVariable Long pedidoId) {
		fluxoService.confirmar(pedidoId);
	}

	@PutMapping("/cancelar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelarrPedido(@PathVariable Long pedidoId) {
		fluxoService.confirmar(pedidoId);
	}
}
