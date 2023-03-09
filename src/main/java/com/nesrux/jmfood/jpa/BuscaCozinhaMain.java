package com.nesrux.jmfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.nesrux.jmfood.JmFoodApiApplication;
import com.nesrux.jmfood.domain.model.Cozinha;

public class BuscaCozinhaMain {
	public static void main(String[] args) {
		ApplicationContext app = new SpringApplicationBuilder(JmFoodApiApplication.class).web(WebApplicationType.NONE)
				.run(args);

		CadastroCozinha cadastroCozinha = app.getBean(CadastroCozinha.class);

		Cozinha cozinha = cadastroCozinha.buscar(1L);
		
		System.out.println(cozinha.getNome());

	}
}
