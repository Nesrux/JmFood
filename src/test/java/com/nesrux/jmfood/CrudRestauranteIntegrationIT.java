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
	private String jsonRestauranteComCozinhaInexistente;
	private String jsonRestauranteComCozinhaNula;
	private String jsonRestauranteComNomeBranco;
	private String jsonRestauranteComNomeNulo;
	private String jsonRestauranteComPropriedadesInvalidas;
	private String jsonRestauranteComTaxaFreteNegativa;
	private String jsonRestauranteComTaxaFreteNulo;
	private String jsonRestauranteComTaxaFreteZero;

	private int restauranteInexistente;

	@Before
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurantes";

		
		jsonRestauranteChines = ResourceUtils.getContentFromResource("/json/correto/RestauranteChines.json");
		jsonRestauranteComCozinhaInexistente = ResourceUtils.getContentFromResource("/json/correto/Restaurante.json");
		jsonRestauranteComCozinhaInexistente = ResourceUtils.getContentFromResource("/json/incorreto/RestauranteComCozinhaInexistente.json");
		jsonRestauranteComCozinhaNula = ResourceUtils.getContentFromResource("/json/incorreto/RestauranteComCozinhaNulla.json");
		jsonRestauranteComNomeBranco = ResourceUtils.getContentFromResource("/json/incorreto/RestauranteComNomeBranco.json");
		jsonRestauranteComNomeNulo=ResourceUtils.getContentFromResource("/json/incorreto/RestauranteComNomeNulo.json");
		jsonRestauranteComPropriedadesInvalidas = ResourceUtils.getContentFromResource("/json/incorreto/RestauranteComPropriedadesInvalidas.json");
		jsonRestauranteComTaxaFreteNegativa =ResourceUtils.getContentFromResource("/json/incorreto/RestauranteComTaxaFreteNegativa.json");
		jsonRestauranteComTaxaFreteNulo= ResourceUtils.getContentFromResource("/json/incorreto/RestauranteComTaxaFreteNulo.json");
		jsonRestauranteComTaxaFreteZero= ResourceUtils.getContentFromResource("/json/incorreto/RestauranteComTaxaFreteZero.json");
		
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
			.statusCode(HttpStatus.OK.value()).body("",hasSize(qtdaRestaurantes));
	}

	@Test
	public void deveRetornar200_quandoConsultarCozinha() {
		given()
			.accept(ContentType.JSON)
			.pathParam("restauranteId", restaurantePadrao.getId())
		.when()
			.get("/{restauranteId}")
		.then().
			statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(restaurantePadrao.getNome()));
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
	@Test
	public void deveRetornar404_QuandoCadasTrarRestauranteComCozinhaInezistente() {
	given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(jsonRestauranteComCozinhaInexistente)
	.when()
		.post()
	.then()
		.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void deveRetornar400_QuandoCadastrarRestauranteComCozinhaNula() {
	given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(jsonRestauranteComCozinhaNula)
	.when()
		.post()
	.then()
		.statusCode(HttpStatus.BAD_REQUEST.value());
		
	}
	@Test
	public void deveRetornar400_QuandoCadastrarRestauranteComNomeEmBranco() {
	given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(jsonRestauranteComNomeBranco)
	.when()
		.post()
	.then()
		.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	@Test
	public void deveRetornar400_QuandoCadastrarUmRestauranteComNomeNulo() {
		given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(jsonRestauranteComNomeNulo)
	.when()
		.post()
	.then()
		.statusCode(HttpStatus.BAD_REQUEST.value());
	
	}
	@Test
	public void deveRetornar000_quandoCadastrarRestauranteComPropriedadeInvalida() {
	given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(jsonRestauranteComPropriedadesInvalidas)
	.when()
		.post()
	.then()
		.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	@Test
	public void deveRetornar400_quandoCadastrarRestauranteComTaxaFreteNula() {
	given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(jsonRestauranteComTaxaFreteNulo)
	.when()
		.post()
	.then()
		.statusCode(HttpStatus.BAD_REQUEST.value());
	
	}
	@Test
	public void deveRetornar400_quandoCadastrarRestauranteComTaxaFretenegativo() {
	given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(jsonRestauranteComTaxaFreteNegativa)
	.when()
		.post()
	.then()
		.statusCode(HttpStatus.BAD_REQUEST.value());
	
	}
	@Test
	public void deveRetornar400_quandoCadastrarRestauranteComTaxaFreteZero() {
	given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(jsonRestauranteComTaxaFreteZero)
	.when()
		.post()
	.then()
		.statusCode(HttpStatus.BAD_REQUEST.value());
	
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
