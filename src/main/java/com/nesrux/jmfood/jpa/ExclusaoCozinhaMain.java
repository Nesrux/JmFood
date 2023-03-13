package com.nesrux.jmfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.nesrux.jmfood.JmFoodApiApplication;
import com.nesrux.jmfood.domain.model.Cozinha;
import com.nesrux.jmfood.domain.repository.CozinhaRepository;

public class ExclusaoCozinhaMain {
	public static void main(String[] args) {
		ApplicationContext app = new SpringApplicationBuilder(JmFoodApiApplication.class).web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository cadastroCozinha = app.getBean(CozinhaRepository.class);

		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Paulista");
		cozinha.setId(1L);

		cadastroCozinha.remover(cozinha);
	}
}
