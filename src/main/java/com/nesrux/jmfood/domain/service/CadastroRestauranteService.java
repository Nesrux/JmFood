package com.nesrux.jmfood.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.RestauranteNaoEncontradoException;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroCozinhaService cozinhaService;
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();

		Cozinha cozinha = cozinhaService.buscaOuFalha(cozinhaId);

		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);
	}
	
	@Transactional
	public void ativar(Long id) {
		Restaurante restaurante = acharOuFalhar(id);
		restaurante.ativar();
	}
	
	@Transactional
	public void desativar(Long id) {
		Restaurante restaurante = acharOuFalhar(id);
		restaurante.desativar();
	}

	public List<Restaurante> acharTodos() {
		return restauranteRepository.findAll();
	}

	public Restaurante acharOuFalhar(Long restauranteId) {
		Restaurante restaurante = restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));

		return restaurante;
	}

}
