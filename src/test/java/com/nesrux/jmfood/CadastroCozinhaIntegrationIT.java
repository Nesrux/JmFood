package com.nesrux.jmfood;

import static io.restassured.RestAssured.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CadastroCozinhaIntegrationIT {
//RunWith vai rodar o código junto com o spring, 
//Teste de intregração e teste DE api

    @LocalServerPort
    private int port;

    @Test
    public void deveRetornarStatus200_quandoConsultar_listasCozinha() {
	RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	given()
		.basePath("/cozinhas")
		.port(port)
		.accept(ContentType.JSON)
	.when()
		.get()
	.then()
		.statusCode(HttpStatus.OK.value());

    }
    @Test
    public void deveRetortar200_quando_consultar_umaCozinha() {
	given().basePath("/cozinhas/1")
		.port(port)
		.accept(ContentType.JSON)
	.when()
		.get()
	.then()
		.statusCode(HttpStatus.OK.value());
    }

}
