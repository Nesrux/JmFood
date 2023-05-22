package com.nesrux.jmfood.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.PedidoNaoEncontradoException;
import com.nesrux.jmfood.domain.model.endereco.Cidade;
import com.nesrux.jmfood.domain.model.pedido.FormaPagamento;
import com.nesrux.jmfood.domain.model.pedido.Pedido;
import com.nesrux.jmfood.domain.model.pedido.Produto;
import com.nesrux.jmfood.domain.model.pedido.StatusPedido;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.model.user.Usuario;
import com.nesrux.jmfood.domain.repository.PedidoRepository;

@Component
public class CadastroPedidoService {
	@Autowired
	private PedidoRepository repository;
	@Autowired
	private CadastroRestauranteService restauranteService;
	@Autowired
	private CadastroFormaPagamentoService formaPagamentoService;
	@Autowired
	private CadastroCidadeService cidadeService;
	@Autowired
	private CadastroProdutoService produtoService;
	@Autowired
	private CadastroUsuarioService usuarioService;

	public List<Pedido> Listar() {
		return repository.findAll();
	}

	public Pedido acharOuFalhar(Long pedidoId) {
		return repository.findById(pedidoId).orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
	}

	@Transactional
	public Pedido emitir(Pedido pedido) {
		validarPedido(pedido);
		verificarItens(pedido);

		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.calcularValorTotal();
		pedido.setStatus(StatusPedido.CRIADO);
		
		return repository.save(pedido);
	}

	private void validarPedido(Pedido pedido) {
		Restaurante restautante = restauranteService.acharOuFalhar(pedido.getRestaurante().getId());
		Cidade cidade = cidadeService.acharOuFalhar(pedido.getEndereco().getCidade().getId());
		FormaPagamento formaPagamento = formaPagamentoService.acharOuFalhar(pedido.getFormaPagamento().getId());
		Usuario usuario = usuarioService.acharOuFalhar(1L);

		pedido.setCliente(usuario);
		pedido.setRestaurante(restautante);
		pedido.getRestaurante().getEndereco().setCidade(cidade);
		pedido.setFormaPagamento(formaPagamento);

		if (restautante.naoAceitaFormaPagamento(formaPagamento)) {
			throw new NegocioException(String.format("O restaurante de id %d não aceita %s como forma de pagamento",
					restautante.getId(), formaPagamento.getDescricao()));
		}

	}

	private void verificarItens(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			Produto produto = produtoService.acharOuFalhar(pedido.getRestaurante().getId(), item.getProduto().getId());
			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getPreco());
		});

	};

	/*
	 * Por padrão todos os pedidos vão estar atribuidos à o usuario de ID 1, pois
	 * eventualmente vou adicioar a autenticação para fazer a validação de usuarios
	 * e a criação de pedidos utilizando o spring securyt
	 */

}
