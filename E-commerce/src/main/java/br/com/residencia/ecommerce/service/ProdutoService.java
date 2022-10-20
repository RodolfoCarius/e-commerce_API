package br.com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.ecommerce.entity.Produto;
import br.com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	public Produto getProdutoById(Integer id) {
		return produtoRepository.findById(id).orElse(null);
	}
	
	public List<Produto> getAllProdutos(){
		return produtoRepository.findAll();
	}
	
	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto updateProduto(Integer id, Produto produto) {
		Produto produtoExistenteNoBanco = getProdutoById(id);
		
		produtoExistenteNoBanco.setDataCadastro(produto.getDataCadastro());
		produtoExistenteNoBanco.setDescricao(produto.getDescricao());
		produtoExistenteNoBanco.setImagem(produto.getImagem());
		produtoExistenteNoBanco.setNome(produto.getNome());
		produtoExistenteNoBanco.setQtdEstoque(produto.getQtdEstoque());
		produtoExistenteNoBanco.setValorUnitario(produto.getValorUnitario());
		
		return produtoRepository.save(produtoExistenteNoBanco);
	}
	
	public Produto deleteProduto(Integer id) {
		produtoRepository.deleteById(id);
		return getProdutoById(id);
	}
}
