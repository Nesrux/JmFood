package com.nesrux.jmfood.core.security;

import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServeConfig extends WebSecurityConfigurerAdapter {

	//Configuração do Basic http
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable().cors()
		.and()
			.oauth2ResourceServer()
			.jwt()
			.jwtAuthenticationConverter(converter());
	}
	
	private JwtAuthenticationConverter converter () {
		var jwtAuth = new JwtAuthenticationConverter();
		jwtAuth.setJwtGrantedAuthoritiesConverter(jwt ->{
			var authorities = jwt.getClaimAsStringList("authorities");
			if(authorities == null) {
				authorities = Collections.emptyList();
			}
			
			return authorities.stream()
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		});
		return jwtAuth;
	}

	//jkaie3049kanc8alpeo029irydhznalep029alsk18s
}
