package com.nesrux.jmfood.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ResourceServeConfig extends WebSecurityConfigurerAdapter {

	//Configuração do Basic http
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
		.and()
		.authorizeRequests()
			.antMatchers("/v1/cozinhas/**").permitAll()
			.antMatchers("/v1/restaurantes/**").permitAll()
			.anyRequest().authenticated()
		.and()
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("joao").password(passEncoder().encode("123")).roles("ADMIN")
			.and()
			.withUser("jmfood123").password(passEncoder().encode("123")).roles("ANY");
	}
	@Bean
	public PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}	
	
	
}
