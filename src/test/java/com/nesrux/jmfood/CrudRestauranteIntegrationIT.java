package com.nesrux.jmfood;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nesrux.jmfood.domain.model.restaurante.Cozinha;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.repository.CozinhaRepository;
import com.nesrux.jmfood.domain.repository.RestauranteRepository;
import com.nesrux.jmfood.util.DataBaseCleaner;

import io.restassured.RestAssured;

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
	private int qtdaCozinhas;
    @Before
    public void setup() {
	RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	RestAssured.port = port;
	RestAssured.basePath = "/cozinhas";
	
	
	//jsonCozinhaChinesa = ResourceUtils.getContentFromResource("/json/correto/CozinhaChinesa.json");
	
	datacleaner.clearTables();
	prepararDados();
   }


	private void prepararDados() {
	Cozinha cozinhaPadrao = new Cozinha();	
	cozinhaPadrao.setNome("Padrão");
	cozinhaRepository.save(cozinhaPadrao);
		
	restaurantePadrao.setNome("EL Padrão");
	restaurantePadrao.setCozinha(cozinhaPadrao);
	restaurantePadrao.setTaxaFrete(taxaFrete);
	repository.save(restaurantePadrao);
	
	qtdaCozinhas = (int) cozinhaRepository.count();
	qtdaRestaurantes = (int) repository.count();
	}

}
