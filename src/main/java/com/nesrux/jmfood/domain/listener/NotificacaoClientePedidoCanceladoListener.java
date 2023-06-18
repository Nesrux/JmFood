package com.nesrux.jmfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.nesrux.jmfood.domain.event.PedidoCanceladoEvent;
import com.nesrux.jmfood.domain.model.pedido.Pedido;
import com.nesrux.jmfood.domain.service.EnvioEmailService;
import com.nesrux.jmfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoCanceladoListener {
	@Autowired
	private EnvioEmailService emailService;

	@TransactionalEventListener
	public void quandoCancelarPedido(PedidoCanceladoEvent event) {
		Pedido pedido = event.getPedido();
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - pedido cancelado")
				.corpo("pedidoCancelado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();

		emailService.enviar(mensagem);
}

}
