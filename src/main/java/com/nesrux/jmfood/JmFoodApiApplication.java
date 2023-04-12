package com.nesrux.jmfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.nesrux.jmfood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class JmFoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmFoodApiApplication.class, args);
	}

}
