package com.nesrux.jmfood.infrastructure.service.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.core.email.EmailProperties;
import com.nesrux.jmfood.domain.service.EnvioEmailService;
import com.nesrux.jmfood.infrastructure.exception.EmailException;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailProperties emailProperties;

	@Override
	public void enviar(Mensagem mensagem) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			// remetente
			helper.setFrom(emailProperties.getRemetente());
			// para quem vai enviar
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			// assunto
			helper.setSubject(mensagem.getAssunto());
			// corpo, com a opção true, significa que vai enviar HTML
			helper.setText(mensagem.getCorpo(), true);

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possivel enviar o e-mail", e);
		}
	}
}
