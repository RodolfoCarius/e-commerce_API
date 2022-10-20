package br.com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.ecommerce.entity.Pedido;
import br.com.residencia.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	public Pedido getPedidoById(Integer id) {
		return pedidoRepository.findById(id).orElse(null);
	}
	
	public List<Pedido> getAllPedidos(){
		return pedidoRepository.findAll();
	}
	
	public Pedido savePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public Pedido updatePedido(Integer id, Pedido pedido) {
		Pedido pedidoExistenteNoBanco = getPedidoById(id);
		
		pedidoExistenteNoBanco.setDataEntrega(pedido.getDataEntrega());
		pedidoExistenteNoBanco.setDataEnvio(pedido.getDataEnvio());
		pedidoExistenteNoBanco.setDataPedido(pedido.getDataPedido());
		pedidoExistenteNoBanco.setStatus(pedido.getStatus());
		pedidoExistenteNoBanco.setValorTotal(pedido.getValorTotal());
		
		return pedidoRepository.save(pedidoExistenteNoBanco);
	}
	
	public Pedido deletePedido(Integer id) {
		pedidoRepository.deleteById(id);
		return getPedidoById(id);
	}
}
