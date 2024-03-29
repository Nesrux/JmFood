package com.nesrux.jmfood.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.RestauranteNaoEncontradoException;
import com.nesrux.jmfood.domain.model.endereco.Cidade;
import com.nesrux.jmfood.domain.model.pedido.FormaPagamento;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.model.user.Usuario;
import com.nesrux.jmfood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroCozinhaService cozinhaService;

	@Autowired
	private CadastroCidadeService cidadeService;

	@Autowired
	private CadastroFormaPagamentoService pagamentoService;

	@Autowired
	private CadastroUsuarioService usuarioService;

	public List<Restaurante> acharTodos() {
		return restauranteRepository.findAll();
	}

	public Restaurante acharOuFalhar(Long restauranteId) {
		Restaurante restaurante = restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));

		return restaurante;
	}

	public List<Usuario> usuariosResponsaveis(Long restauranteId) {
		Restaurante restaurante = acharOuFalhar(restauranteId);
		return restaurante.getResponsaveis().stream().collect(Collectors.toList());
	}

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();

		Cozinha cozinha = cozinhaService.buscaOuFalha(cozinhaId);
		Cidade cidade = cidadeService.acharOuFalhar(cidadeId);

		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);

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

	@Transactional
	public void ativar(List<Long> restaurantesId) {
		restaurantesId.forEach(this::ativar);
	}
	
	@Transactional
	public void desativar(List<Long> restaurantesId) {
		restaurantesId.forEach(this::desativar);
	}


	@Transactional
	public void desassociarFormaPagamento(Long restauranteID, Long formaPagamentoID) {
		Restaurante restaurante = acharOuFalhar(restauranteID);
		FormaPagamento formaPagamento = pagamentoService.acharOuFalhar(formaPagamentoID);

		restaurante.removerFormaPagamento(formaPagamento);
	}

	@Transactional
	public void associarFormaPagamento(Long restauranteID, Long formaPagamentoID) {
		Restaurante restaurante = acharOuFalhar(restauranteID);
		FormaPagamento formaPagamento = pagamentoService.acharOuFalhar(formaPagamentoID);

		restaurante.adicionarFormaPagamento(formaPagamento);
	}

	@Transactional
	public void abrir(Long restauranteId) {
		Restaurante restaurante = acharOuFalhar(restauranteId);
		restaurante.abrir();
	}

	@Transactional
	public void fechar(Long restauranteId) {
		Restaurante restaurante = acharOuFalhar(restauranteId);
		restaurante.fechar();
	}

	@Transactional
	public void associarFuncionario(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = acharOuFalhar(restauranteId);
		Usuario usuario = usuarioService.acharOuFalhar(usuarioId);

		restaurante.associarUsuario(usuario);
	}

	@Transactional
	public void desassociarFuncionario(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = acharOuFalhar(restauranteId);
		Usuario usuario = usuarioService.acharOuFalhar(usuarioId);

		restaurante.desassociarUsuario(usuario);
	}

}
