package br.com.residencia.ecommerce.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="itempedido")
public class Item_pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iditempedido")
	private Integer idItemPedido;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@Column(name = "precovenda")
	private BigDecimal precoVenda;
	
	@Column(name = "percentualdesconto")
	private Integer percentualDesconto;
	
	@Column(name = "valorbruto")
	private BigDecimal valorBruto;
	
	@Column(name = "valorliquido")
	private BigDecimal valorLiquido;
	
	@ManyToMany
	@JoinColumn(name = "idpedido", referencedColumnName = "idpedido")
	private Pedido pedido;
	
	@ManyToMany
	@JoinColumn(name = "idproduto", referencedColumnName = "idprduto")
	private Produto produto;
	
	public Item_pedido() {
	}

	public Item_pedido(Integer idItemPedido, Integer quantidade, BigDecimal precoVenda, Integer percentualDesconto,
			BigDecimal valorBruto, BigDecimal valorLiquido, Pedido pedido, Produto produto) {
		this.idItemPedido = idItemPedido;
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.percentualDesconto = percentualDesconto;
		this.valorBruto = valorBruto;
		this.valorLiquido = valorLiquido;
		this.pedido = pedido;
		this.produto = produto;
	}

	public Integer getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Integer getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Integer percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	
}
