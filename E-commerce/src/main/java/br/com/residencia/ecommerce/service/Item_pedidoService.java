package br.com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.ecommerce.entity.Item_pedido;
import br.com.residencia.ecommerce.repository.Item_pedidoRepository;

@Service
public class Item_pedidoService {

	@Autowired
	Item_pedidoRepository item_pedidoRepository;
	
	public Item_pedido getItem_pedidoById(Integer id) {
		return item_pedidoRepository.findById(id).orElse(null);
	}
	
	public List<Item_pedido> getAllItem_pedidos(){
		return item_pedidoRepository.findAll();
	}
	
	public Item_pedido saveItem_pedido(Item_pedido item_pedido) {
		return item_pedidoRepository.save(item_pedido);
	}
	
	public Item_pedido updateItem_pedido(Integer id, Item_pedido item_pedido) {
		Item_pedido item_pedidoExistenteNoBanco = getItem_pedidoById(id);
		
		item_pedidoExistenteNoBanco.setPercentualDesconto(item_pedido.getPercentualDesconto());
		item_pedidoExistenteNoBanco.setPrecoVenda(item_pedido.getPrecoVenda());
		item_pedidoExistenteNoBanco.setQuantidade(item_pedido.getQuantidade());
		item_pedidoExistenteNoBanco.setValorBruto(item_pedido.getValorBruto());
		item_pedidoExistenteNoBanco.setValorLiquido(item_pedido.getValorLiquido());
		
		return item_pedidoRepository.save(item_pedidoExistenteNoBanco);
	}
	
	public Item_pedido deleteItem_pedido(Integer id) {
		item_pedidoRepository.deleteById(id);
		return getItem_pedidoById(id);
	}
}
