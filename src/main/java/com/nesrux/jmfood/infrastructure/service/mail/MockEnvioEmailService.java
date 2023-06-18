package com.nesrux.jmfood.infrastructure.service.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.nesrux.jmfood.core.email.EmailProperties;
import com.nesrux.jmfood.domain.service.EnvioEmailService;
import com.nesrux.jmfood.infrastructure.exception.EmailException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MockEnvioEmailService implements EnvioEmailService {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailProperties emailProperties;

	@Autowired
	private Configuration freemarkerConfig;

	@Override
	public void enviar(Mensagem mensagem) {
		try {
			String corpoEmHtml = processarTemplate(mensagem);

			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			// remetente
			helper.setFrom(emailProperties.getRemetente());
			// para quem vai enviar
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			// assunto
			helper.setSubject(mensagem.getAssunto());
			// corpo, com a opção true, significa que vai enviar HTML
			helper.setText(corpoEmHtml, true);

			log.info("O Email foi enviado com sucesso!");
			
		} catch (Exception e) {
			throw new EmailException("Não foi possivel enviar o e-mail", e);
		}

	}

	private String processarTemplate(Mensagem mensagem) {
		try {
			Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());

			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
		} catch (Exception e) {
			throw new EmailException("Não foi possivel criar o template do email");
		}

	}

}
