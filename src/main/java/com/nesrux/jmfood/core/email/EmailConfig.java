package com.nesrux.jmfood.core.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class EmailConfig {	

	@Autowired
	private EmailProperties emailProperties;

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(emailProperties.getHost());
		mailSender.setPort(emailProperties.getPort());
		mailSender.setUsername(emailProperties.getRemetente());
		mailSender.setPassword(emailProperties.getPassword());

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.connectiontimeout", 10000);

		mailSender.setJavaMailProperties(props);

		return mailSender;

	}
}