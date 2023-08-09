package com.nesrux.jmfood.core.security.authorizationServer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Component
@ConfigurationProperties("jmfood.jwt")
@Getter
@Setter
public class JwtKeyStoreProperties {

	@NotNull
	private Resource jksLocation;

	@NotBlank
	private String alias;

	@NotBlank
	private String password;



}