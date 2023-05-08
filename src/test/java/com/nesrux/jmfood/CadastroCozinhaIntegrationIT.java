package com.nesrux.jmfood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
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
import com.nesrux.jmfood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "/application-test.properties")
public class CadastroCozinhaIntegrationIT {
//RunWith vai rodar o código junto com o spring, 
//Teste de intregração e teste DE api
	private static final int COZINHA_INEXISTENTE = 10000;	

	@Autowired
	private DataBaseCleaner datacleaner; //essa classe foi gerada a partir de um código de terceiros 
	@Autowired
	private CozinhaRepository repository;
	
    @LocalServerPort
    private int port;
    

    private Cozinha cozinhaBrasileira = new Cozinha();
    private int quantidadeDeCozinhas;
    private String jsonCozinhaChinesa;
   
    
    @Before
    public void setup() {
	RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	RestAssured.port = port;
	RestAssured.basePath = "/cozinhas";
	
	
	jsonCozinhaChinesa = ResourceUtils.getContentFromResource("/json/correto/CozinhaChinesa.json");
	
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
		.pathParam("cozinhaId", cozinhaBrasileira.getId())
	.when()
		.get("/{cozinhaId}")
	.then()
		.statusCode(HttpStatus.OK.value())
		.body("nome", equalTo(cozinhaBrasileira.getNome()));
    }
    
    @Test
    public void deveConter3Cozinhas() {
	given()
		.accept(ContentType.JSON)
	.when()
		.get()
	.then()
		.body("", hasSize(quantidadeDeCozinhas));
	}
    
    @Test
    public void deveRetornarStatus201_quandoCadastrarCozinha() {
	given()
		.body(jsonCozinhaChinesa)
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
    		.pathParam("cozinhaId",1)
    	.when()
    		.delete("/{cozinhaId}")
    	.then()
    		.statusCode(HttpStatus.NO_CONTENT.value());
    }
    @Test
    public void deveRetornar404_quandoConxultarCozinhaInexistente() {
       	given()
		.accept(ContentType.JSON)
		.pathParam("cozinhaId", COZINHA_INEXISTENTE)
	.when()
		.get("/{cozinhaId}")
	.then()
		.statusCode(HttpStatus.NOT_FOUND.value());
    }
    
    private void prepararDados() {
    	Cozinha cozinha1 = new Cozinha();
    	cozinha1.setNome("Italiana");

    	cozinhaBrasileira.setNome("Brasileira");
    	
    	Cozinha cozinha3 = new Cozinha();
    	cozinha3.setNome("Italiana");
    	
    	
    	repository.save(cozinha1);
    	repository.save(cozinhaBrasileira);
    	repository.save(cozinha3);
    	
    	quantidadeDeCozinhas = (int) repository.count();
    }
}
