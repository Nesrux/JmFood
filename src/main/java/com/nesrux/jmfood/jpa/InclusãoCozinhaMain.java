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

		Cozinha cozinha = new Cozinha();
		Cozinha cozinha2 = new Cozinha();
		Cozinha cozinha3 = new Cozinha();
		Cozinha cozinha4 = new Cozinha();

		cozinha.setNome("paulista");
		cozinha2.setNome("adventista");
		cozinha3.setNome("taxista");
		cozinha4.setNome("otorrinarigologista");
		
		cadastroCozinha.salvar(cozinha);
		cadastroCozinha.salvar(cozinha2);
		cadastroCozinha.salvar(cozinha3);
		cadastroCozinha.salvar(cozinha4);
	}
}
