package com.nesrux.jmfood.core.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Validated
@Component
@ConfigurationProperties("jmfood.email")
@Getter
@Setter
public class EmailProperties {
	
	@NonNull
	private String remetente;
	@NonNull
	private String password;
	@NonNull
	private Integer port;
	@NonNull
	private String host;
	
	
}
