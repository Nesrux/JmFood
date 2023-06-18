package com.nesrux.jmfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.nesrux.jmfood.domain.event.PedidoConfirmadoEvent;
import com.nesrux.jmfood.domain.model.pedido.Pedido;
import com.nesrux.jmfood.domain.service.EnvioEmailService;
import com.nesrux.jmfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {
	@Autowired
	private EnvioEmailService emailService;

	@TransactionalEventListener
	public void quandoConfirmarPedido(PedidoConfirmadoEvent event) {
		Pedido pedido = event.getPedido();
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante()
				.getNome() + " - pedido confirmado")
				.corpo("pedidoConfirmado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();

		emailService.enviar(mensagem);

	}

}
