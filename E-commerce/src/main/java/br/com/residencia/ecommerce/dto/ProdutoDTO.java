package br.com.residencia.ecommerce.dto;

import java.util.List;

public class ProdutoDTO {

	private Integer idProduto;
	private String nomeProduto;
	private List<ItemPedidoDTO> listaItensPedidosProdutosDTO;
	
	public ProdutoDTO() {
	}
	
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public List<ItemPedidoDTO> getListaItensPedidosProdutosDTO() {
		return listaItensPedidosProdutosDTO;
	}

	public void setListaItensPedidosProdutosDTO(List<ItemPedidoDTO> listaItensPedidosProdutosDTO) {
		this.listaItensPedidosProdutosDTO = listaItensPedidosProdutosDTO;
	}
	
	
}
