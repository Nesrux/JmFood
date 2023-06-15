package com.nesrux.jmfood.core.email;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Component
@ConfigurationProperties("jmfood.email")
@Getter
@Setter
public class EmailProperties {
	
	@NotNull
	private String remetente;

}
