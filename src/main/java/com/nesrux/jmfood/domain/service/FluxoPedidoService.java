package com.nesrux.jmfood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.model.pedido.Pedido;
import com.nesrux.jmfood.domain.service.EnvioEmailService.Mensagem;

@Service
public class FluxoPedidoService {
	@Autowired
	private CadastroPedidoService pedidoService;

	@Autowired
	private EnvioEmailService emailService;
	
	@Transactional
	public void confirmar(String codigoPedido) {
		Pedido pedido = pedidoService.acharOuFalhar(codigoPedido);
		pedido.confirmarPedido();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - pedido confirmado")
				.corpo("pedidoConfirmado.html")
				.destinatario(pedido.getCliente().getEmail())
				.build();	

		emailService.enviar(mensagem);

	}

	@Transactional
	public void entregar(String codigoPedido) {
		Pedido pedido = pedidoService.acharOuFalhar(codigoPedido);
		pedido.entregarPedido();

	}

	@Transactional
	public void cancelar(String codigoPedido) {
		Pedido pedido = pedidoService.acharOuFalhar(codigoPedido);
		pedido.cancelarPedido();
	}

}
