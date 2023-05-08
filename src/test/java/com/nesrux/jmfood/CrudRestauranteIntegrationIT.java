package com.nesrux.jmfood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nesrux.jmfood.domain.model.restaurante.Cozinha;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.repository.CozinhaRepository;
import com.nesrux.jmfood.domain.repository.RestauranteRepository;
import com.nesrux.jmfood.util.DataBaseCleaner;
import com.nesrux.jmfood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "/application-test.properties")
public class CrudRestauranteIntegrationIT {

	@LocalServerPort
	private int port;

	@Autowired
	private DataBaseCleaner datacleaner;

	@Autowired
	private CozinhaRepository cozinhaRepository;
	@Autowired
	private RestauranteRepository repository;

	private Restaurante restaurantePadrao = new Restaurante();
	private BigDecimal taxaFrete = BigDecimal.valueOf(4);
	private int qtdaRestaurantes;
	private String jsonRestauranteChines;
	private int restauranteInexistente;
	
	@Before
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurantes";

		
		jsonRestauranteChines = ResourceUtils.getContentFromResource("/json/correto/RestauranteChines.json");
		
		datacleaner.clearTables();
		prepararDados();
		restauranteInexistente = qtdaRestaurantes + 1;
	}
	
	@Test
	public void deveRetornar200_quandoConsultarTodasCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("", hasSize(qtdaRestaurantes));
	}
	@Test
	public void deveRetornar200_quandoConsultarCozinha() {
		given()
		.accept(ContentType.JSON)
			.pathParam("restauranteId", restaurantePadrao.getId())
		.when()
			.get("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.OK.value())
		.	body("nome",equalTo(restaurantePadrao.getNome()));
	}
	
	@Test
	public void deveRetornar201_quandoCadastrarUmaCozinha() {
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(jsonRestauranteChines)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	@Test
	public void deveRetornar404_quandoConsultarRestauranteInexistente() {
		given()
		.accept(ContentType.JSON)
			.pathParam("restauranteId", restauranteInexistente)
		.when()
			.get("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());		
	}
	
	private void prepararDados() {
		Cozinha cozinhaPadrao = new Cozinha();
		cozinhaPadrao.setNome("chinesa");
		cozinhaRepository.save(cozinhaPadrao);

		restaurantePadrao.setNome("EL Padrão");
		restaurantePadrao.setCozinha(cozinhaPadrao);
		restaurantePadrao.setTaxaFrete(taxaFrete);
		repository.save(restaurantePadrao);

		qtdaRestaurantes = (int) repository.count();
	}

}
