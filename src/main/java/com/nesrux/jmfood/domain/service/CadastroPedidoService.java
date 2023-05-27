package com.nesrux.jmfood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.PedidoNaoEncontradoException;
import com.nesrux.jmfood.domain.filter.PedidoFilter;
import com.nesrux.jmfood.domain.model.endereco.Cidade;
import com.nesrux.jmfood.domain.model.endereco.Estado;
import com.nesrux.jmfood.domain.model.pedido.FormaPagamento;
import com.nesrux.jmfood.domain.model.pedido.Pedido;
import com.nesrux.jmfood.domain.model.pedido.Produto;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.model.user.Usuario;
import com.nesrux.jmfood.domain.repository.PedidoRepository;
import com.nesrux.jmfood.infrastructure.repository.spec.PedidoSpecs;

@Service
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
	
	@Autowired
	private CadastroEstadoService estadoService;

	// TODO aprende a usar o BIG decimal, PQ PQP VIU

	public Page<Pedido> Listar(PedidoFilter pedido, Pageable page) {
		return repository.findAll(PedidoSpecs.usandoFiltro(pedido), page);
	}

	public Pedido acharOuFalhar(String codigoPedido) {
		return repository.findByCodigo(codigoPedido).orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
	}

	@Transactional
	public Pedido emitir(Pedido pedido) {
		validarPedido(pedido);
		verificarItens(pedido);
		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.calcularValorTotal();
		pedido.criarPedido();
		return repository.save(pedido);
	}

	private void validarPedido(Pedido pedido) {
		Restaurante restautante = restauranteService.acharOuFalhar(pedido.getRestaurante().getId());
		Cidade cidade = cidadeService.acharOuFalhar(pedido.getEndereco().getCidade().getId());
		FormaPagamento formaPagamento = formaPagamentoService.acharOuFalhar(pedido.getFormaPagamento().getId());
		Usuario usuario = usuarioService.acharOuFalhar(pedido.getCliente().getId());

		pedido.setRestaurante(restautante);
		pedido.getRestaurante().getEndereco().setCidade(cidade);
		pedido.setFormaPagamento(formaPagamento);
		pedido.setCliente(usuario);
		
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
