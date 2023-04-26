package com.nesrux.jmfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.nesrux.jmfood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class JmFoodApiApplication {
	/*
	 * A anootação @EnableJpaRepositories faz comque se sobrescreva a
	 * implementação do jpa padrão criando asssim 
	 */
	public static void main(String[] args) {
		SpringApplication.run(JmFoodApiApplication.class, args);
	}

	/*
	 * Essa classe é o main do projeto, é por ela que o projeto inicia e é por
	 * ela que todo o projeto funciona e onde ela rebele as requsiçoes etc
	 */

}
