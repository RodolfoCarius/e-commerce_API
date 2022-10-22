package br.com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.ecommerce.entity.Cliente;
import br.com.residencia.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public Cliente getClienteById(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}

	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}

	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente updateCliente(Integer id, Cliente cliente) {
		Cliente clienteExistenteNoBanco = getClienteById(id);

		clienteExistenteNoBanco.setNomeCompleto(cliente.getNomeCompleto());
		clienteExistenteNoBanco.setCpf(cliente.getCpf());
		clienteExistenteNoBanco.setDataNascimento(cliente.getDataNascimento());
		clienteExistenteNoBanco.setEmail(cliente.getEmail());
		clienteExistenteNoBanco.setTelefone(cliente.getTelefone());

		return clienteRepository.save(clienteExistenteNoBanco);
	}

	public Cliente deleteCliente(Integer id) {
		clienteRepository.deleteById(id);
		return getClienteById(id);
	}
}
