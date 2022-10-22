package br.com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.residencia.ecommerce.entity.ItemPedido;
import br.com.residencia.ecommerce.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	ItemPedidoRepository itempedidoRepository;

	public ItemPedido getItempedidoById(Integer id) {
		return itempedidoRepository.findById(id).orElse(null);
	}

	public List<ItemPedido> getAllItempedidos() {
		return itempedidoRepository.findAll();
	}

	public ItemPedido saveItempedido(ItemPedido itempedido) {
		return itempedidoRepository.save(itempedido);
	}

	public ItemPedido updateItempedido(Integer id, ItemPedido itempedido) {
		ItemPedido item_pedidoExistenteNoBanco = getItempedidoById(id);

		item_pedidoExistenteNoBanco.setPercentualDesconto(itempedido.getPercentualDesconto());
		item_pedidoExistenteNoBanco.setPrecoVenda(itempedido.getPrecoVenda());
		item_pedidoExistenteNoBanco.setQuantidade(itempedido.getQuantidade());
		item_pedidoExistenteNoBanco.setValorBruto(itempedido.getValorBruto());
		item_pedidoExistenteNoBanco.setValorLiquido(itempedido.getValorLiquido());

		return itempedidoRepository.save(item_pedidoExistenteNoBanco);
	}

	public ItemPedido deleteItempedido(Integer id) {
		itempedidoRepository.deleteById(id);
		return getItempedidoById(id);
	}
}
