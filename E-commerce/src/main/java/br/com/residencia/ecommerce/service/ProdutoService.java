package br.com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.ecommerce.dto.ProdutoDTO;
import br.com.residencia.ecommerce.entity.Produto;
import br.com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> getAllProdutos() {
		return produtoRepository.findAll();
	}

	public List<ProdutoDTO> getAllProdutosDTO() {
		List<Produto> listaProduto = produtoRepository.findAll();
		List<ProdutoDTO> listaProdutoDTO = new ArrayList<>();
		
		
		for (Produto produto : listaProduto) {

			ProdutoDTO produtoDTO = toDTO(produto);

			listaProdutoDTO.add(produtoDTO);
		}

		return listaProdutoDTO;
	}

	public Produto getProdutoById(Integer id) {
		//return produtoRepository.findById(id).orElse(null);
		return produtoRepository.findById(id).get();
	}

	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public ProdutoDTO saveProdutoDTO(ProdutoDTO produtoDTO) {
		Produto produto = toEntidade(produtoDTO);
		Produto novaProduto = produtoRepository.save(produto);

		ProdutoDTO produtoAtualizadaDTO = toDTO(novaProduto);
		return produtoAtualizadaDTO;
	}

	public Produto updateProduto(Produto produto, Integer id) {
		Produto produtoExistenteNoBanco = getProdutoById(id);

		if (produtoExistenteNoBanco != null) {
			//produtoExistenteNoBanco.setIdProduto(produtoExistenteNoBanco.getIdProduto());
			produtoExistenteNoBanco.setNome(produto.getNome());
			produtoExistenteNoBanco.setDescricao(produto.getDescricao());
			produtoExistenteNoBanco.setQtdEstoque(produto.getQtdEstoque());
			produtoExistenteNoBanco.setDataCadastro(produto.getDataCadastro());
			produtoExistenteNoBanco.setValorUnitario(produto.getValorUnitario());
			produtoExistenteNoBanco.setImagem(produto.getImagem());
			// produtoExistenteNoBanco.setCategoria(produto.getCategoria());

		}
		return produtoRepository.save(produtoExistenteNoBanco);
	}

	public ProdutoDTO updateProdutoDTO(ProdutoDTO produtoDTO, Integer id) {
		Produto produtoExistenteNoBanco = getProdutoById(id);
		ProdutoDTO produtoAtualizadaDTO = new ProdutoDTO();
		produtoDTO.setIdProduto(id);
		if(produtoExistenteNoBanco != null) {
			
			produtoExistenteNoBanco = toEntidade(produtoDTO);
			
			Produto produtoAtualizada = produtoRepository.save(produtoExistenteNoBanco);
			
			produtoAtualizadaDTO = toDTO(produtoAtualizada);
			
		}
		return produtoAtualizadaDTO;
	}

	public Produto deleteProduto(Integer id) {
		produtoRepository.deleteById(id);
		return getProdutoById(id);
	}

	public Produto toEntidade(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		
		produto.setIdProduto(produtoDTO.getIdProduto());
		produto.setNome(produtoDTO.getNomeProduto());
		
		return produto;
	}

	public ProdutoDTO toDTO(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();

		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setNomeProduto(produto.getNome());

		return produtoDTO;
	}
}
