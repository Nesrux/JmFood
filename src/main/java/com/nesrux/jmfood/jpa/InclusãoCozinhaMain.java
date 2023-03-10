package com.nesrux.jmfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.nesrux.jmfood.JmFoodApiApplication;
import com.nesrux.jmfood.domain.model.Cozinha;
import com.nesrux.jmfood.domain.repository.CozinhaRepository;

public class InclusãoCozinhaMain {
	public static void main(String[] args) {
		ApplicationContext app = new SpringApplicationBuilder(JmFoodApiApplication.class).web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository cadastroCozinha = app.getBean(CozinhaRepository.class);

		Cozinha cozinha = new Cozinha("Paulista");
		Cozinha cozinha2 = new Cozinha("Carioca");
		Cozinha cozinha3 = new Cozinha("Mineira");
		Cozinha cozinha4 = new Cozinha("Cearense");

		cadastroCozinha.salvar(cozinha);
		cadastroCozinha.salvar(cozinha2);
		cadastroCozinha.salvar(cozinha3);
		cadastroCozinha.salvar(cozinha4);
	}
}
