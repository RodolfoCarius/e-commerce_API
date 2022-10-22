package br.com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.ecommerce.dto.ItemPedidoDTO;
import br.com.residencia.ecommerce.entity.ItemPedido;
import br.com.residencia.ecommerce.repository.ItemPedidoRepository;
import br.com.residencia.ecommerce.repository.PedidoRepository;
import br.com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class ItemPedidoService {
	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;

	public List<ItemPedido> getAllItensPedidos() {
		return itemPedidoRepository.findAll();
	}
	
	public List<ItemPedidoDTO> getAllItensPedidosDTO() {
		List<ItemPedido> listaItemPedido = itemPedidoRepository.findAll();
		List<ItemPedidoDTO> listaItemPedidoDTO = new ArrayList<>();

		for (ItemPedido itemPedido : listaItemPedido) {

			ItemPedidoDTO itemPedidoDTO = toDTO(itemPedido);

			listaItemPedidoDTO.add(itemPedidoDTO);
		}

		return listaItemPedidoDTO;
	}

	public ItemPedido getItemPedidoById(Integer id) {
		//return itemPedidoRepository.findById(id).orElse(null);
		return itemPedidoRepository.findById(id).get();
	}
	
	public ItemPedido saveItemPedido(ItemPedido itemPedido) {
		itemPedido.setPedido(pedidoRepository.findById(itemPedido.getPedido().getIdPedido()).orElse(null));
		itemPedido.setProduto (produtoRepository.findById(itemPedido.getProduto().getIdProduto()).orElse(null));
		
		return itemPedidoRepository.save(itemPedido);
	}
	
	public ItemPedidoDTO saveItemPedidoDTO(ItemPedidoDTO itemPedidoDTO) {
		ItemPedido itemPedido = toEntidade(itemPedidoDTO);
		ItemPedido novaItemPedido = itemPedidoRepository.save(itemPedido);

		ItemPedidoDTO itemPedidoAtualizadaDTO = toDTO(novaItemPedido);
		return itemPedidoAtualizadaDTO;
	}

	public ItemPedido updateItemPedido(ItemPedido itemPedido, Integer id) {
		ItemPedido itemPedidoExistenteNoBanco = getItemPedidoById(id);

		if (itemPedidoExistenteNoBanco != null) {
			itemPedidoExistenteNoBanco.setQuantidade(itemPedido.getQuantidade());
			itemPedidoExistenteNoBanco.setPrecoVenda(itemPedido.getPrecoVenda());
			itemPedidoExistenteNoBanco.setPercentualDesconto(itemPedido.getPercentualDesconto());
			itemPedidoExistenteNoBanco.setValorBruto(itemPedido.getValorBruto());
			itemPedidoExistenteNoBanco.setValorLiquido(itemPedido.getValorLiquido());
			//itemPedidoExistenteNoBanco.setPedido(itemItemPedido.getPedido());
			//itemPedidoExistenteNoBanco.setIdItemPedido(itemPedido.getIdItemPedido());
			//itemPedidoExistenteNoBanco.setProduto(itemPedido.getProduto());
		
		
		}
		return itemPedidoRepository.save(itemPedidoExistenteNoBanco);
	}
	
	public ItemPedidoDTO updateItemPedidoDTO(ItemPedidoDTO itemPedidoDTO, Integer id) {
		ItemPedido itemPedidoExistenteNoBanco = getItemPedidoById(id);
		ItemPedidoDTO itemPedidoAtualizadaDTO = new ItemPedidoDTO();
		itemPedidoDTO.setIdItemPedido(id);
		
		if(itemPedidoExistenteNoBanco != null) {
			
			itemPedidoExistenteNoBanco = toEntidade(itemPedidoDTO);
			
			ItemPedido itemPedidoAtualizada = itemPedidoRepository.save(itemPedidoExistenteNoBanco);
			
			itemPedidoAtualizadaDTO = toDTO(itemPedidoAtualizada);
			
		}
		return itemPedidoAtualizadaDTO;
	}

	public ItemPedido deleteItemPedido(Integer id) {
		itemPedidoRepository.deleteById(id);
		return getItemPedidoById(id);
	}
	
	private ItemPedido toEntidade(ItemPedidoDTO itemPedidoDTO) {
		ItemPedido itemPedido = new ItemPedido();
		
		  itemPedido.setIdItemPedido(itemPedidoDTO.getIdItemPedido());
		itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
		itemPedido.setPrecoVenda(itemPedidoDTO.getPrecoVenda());
		itemPedido.setPercentualDesconto(itemPedidoDTO.getPercentualDesconto());
		itemPedido.setValorBruto(itemPedidoDTO.getValorBruto());
		itemPedido.setValorLiquido(itemPedidoDTO.getValorLiquido());
		//itemPedido.setPedido(itemPedidoDTO);
		//itemPedido.setProduto(itemPedidoDTO.getProdutosDTO());
		return itemPedido;
	}

	private ItemPedidoDTO toDTO(ItemPedido itemPedido) {
		ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();

		itemPedidoDTO.setIdItemPedido(itemPedido.getIdItemPedido());
		itemPedidoDTO.setQuantidade(itemPedido.getQuantidade());
		itemPedidoDTO.setPrecoVenda(itemPedido.getPrecoVenda());
		itemPedidoDTO.setPercentualDesconto(itemPedido.getPercentualDesconto());
		itemPedidoDTO.setValorBruto(itemPedido.getValorBruto());
		itemPedidoDTO.setValorLiquido(itemPedido.getValorLiquido());
		//itemPedidoDTO.setPedidosDTO(itemPedido.getPedido());
		//itemPedidoDTO.setProdutos(itemPedido.getProduto());
		
		return itemPedidoDTO;
	}

}
