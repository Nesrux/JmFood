package com.nesrux.jmfood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

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
import com.nesrux.jmfood.domain.repository.CozinhaRepository;
import com.nesrux.jmfood.util.DataBaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
//@ActiveProfiles ("test")
@TestPropertySource(locations = "/application-test.properties")
public class CadastroCozinhaIntegrationIT {
//RunWith vai rodar o código junto com o spring, 
//Teste de intregração e teste DE api
	@Autowired
	private DataBaseCleaner datacleaner; //essa classe foi gerada a partir de um código de terceiros 
	@Autowired
	private CozinhaRepository repository;
	
    @LocalServerPort
    private int port;

    @Before
    public void setup() {
	RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	RestAssured.port = port;
	RestAssured.basePath = "/cozinhas";
	
	datacleaner.clearTables();
	prepararDados();
   }
    
    @Test
    public void deveRetornarStatus200_quandoConsultar_listasCozinha() {
	given()
		.accept(ContentType.JSON)
	.when()
		.get()
	.then()
		.statusCode(HttpStatus.OK.value());

    }
    @Test
    public void deveRetortar200_quando_consultar_umaCozinha() {
	given()
		.accept(ContentType.JSON)
	.when()
		.get("/1")
	.then()
		.statusCode(HttpStatus.OK.value());
    }
    
    @Test
    public void deveConter3Cozinhas() {
	given()
		.accept(ContentType.JSON)
	.when()
		.get()
	.then()
		.body("", hasSize(3))
		.body("nome",hasItems("Chinesa", "Brasileira", "Italiana"));
    }
    
    @Test
    public void deveRetornarStatus201_quandoCadastrarCozinha() {
	given()
		.body("{\"nome\" : \"Chinesa\"}")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
	.when()
		.post()
	.then()
		.statusCode(HttpStatus.CREATED.value());
    }
    @Test
    public void deveRetornarCodigo204_QuandoDeletarCozinha() {
    	given()
    		.accept(ContentType.JSON)
    	.when()
    		.delete("/1")
    	.then()
    		.statusCode(HttpStatus.NO_CONTENT.value());
    }
    
    private void prepararDados() {
    	Cozinha cozinha1 = new Cozinha();
    	cozinha1.setNome("Chinesa");

    	Cozinha cozinha2 = new Cozinha();
    	cozinha2.setNome("Brasileira");

    	Cozinha cozinha3 = new Cozinha();
    	cozinha3.setNome("Italiana");
    	
    	repository.save(cozinha1);
    	repository.save(cozinha2);
    	repository.save(cozinha3);
    }
}
