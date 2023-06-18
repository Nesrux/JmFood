package com.nesrux.jmfood.core.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("jmfood.email.sandbox")
@Getter
@Setter
public class SandBoxEmailProperties {
	
	private String destinatario;

}
