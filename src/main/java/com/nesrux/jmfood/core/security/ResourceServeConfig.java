package com.nesrux.jmfood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ResourceServeConfig extends WebSecurityConfigurerAdapter {

	//Configuração do Basic http
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests().anyRequest().authenticated()
		.and()
			.cors()
		.and()
			.oauth2ResourceServer().jwt();
	}
	

	//jkaie3049kanc8alpeo029irydhznalep029alsk18s
}
