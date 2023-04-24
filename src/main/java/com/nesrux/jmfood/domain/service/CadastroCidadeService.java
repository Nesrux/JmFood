package com.nesrux.jmfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.exception.EntidadeEmUsoException;
import com.nesrux.jmfood.domain.exception.EntidadeNaoEncontradaException;
import com.nesrux.jmfood.domain.model.endereco.Cidade;
import com.nesrux.jmfood.domain.model.endereco.Estado;
import com.nesrux.jmfood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	private static final String MSG_CIDADE_NAO_ENCONTRADAA = "Não existe um cadastro de cidade com código %d";

	private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroEstadoService estadoService;

	public Cidade salvar(Cidade cidade) {
		//Pega o id do estado cadastrado na cidade
		Long estadoId = cidade.getEstado().getId();
		//busca o estado por o id encontrado dentro da entidade cidade
		Estado estado =  estadoService.acharOuFalhar(estadoId);
		//seta o novo estado (se vier)
		cidade.setEstado(estado);
		//salva o estado novo
		return cidadeRepository.save(cidade);

	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
		} catch (IllegalArgumentException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADAA, cidadeId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}

	public Cidade acharOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADAA, cidadeId)));
	}
	
	public List<Cidade> acharTodas(){
		return cidadeRepository.findAll();
	}

}