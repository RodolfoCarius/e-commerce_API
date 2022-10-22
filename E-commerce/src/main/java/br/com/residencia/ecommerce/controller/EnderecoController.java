package br.com.residencia.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.residencia.ecommerce.dto.ConsultaCepDTO;
import br.com.residencia.ecommerce.entity.Endereco;
import br.com.residencia.ecommerce.exception.NoSuchElementFoundException;
import br.com.residencia.ecommerce.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	@Autowired
	EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<List<Endereco>> getAllEnderecos() {
		return new ResponseEntity<>(enderecoService.getAllEnderecos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> getEnderecoById(@PathVariable Integer id) {
		Endereco endereco = new Endereco();

		try {
			endereco = enderecoService.getEnderecoById(id);
			return new ResponseEntity<>(endereco, HttpStatus.OK);
		} catch (Exception ex) {
			throw new NoSuchElementFoundException("Não foi encontrado resultado com o id " + id);
		}
	}

	/*
	 * @GetMapping("/{id}") public ResponseEntity<Endereco>
	 * getEnderecoById(@PathVariable Integer id) { Endereco endereco =
	 * enderecoService.getEnderecoById(id); if(null != endereco) return new
	 * ResponseEntity<>(endereco, HttpStatus.OK); else return new
	 * ResponseEntity<>(endereco, HttpStatus.NOT_FOUND); }
	 */

	@GetMapping("/consulta-cep/{cep}")
	public ResponseEntity<ConsultaCepDTO> consultaCepApiExterna(@PathVariable String cep) {
		ConsultaCepDTO consultaCepDTO = enderecoService.consultaCepApiExterna(cep);
		if (null != consultaCepDTO)
			return new ResponseEntity<>(consultaCepDTO, HttpStatus.OK);
		else
			return new ResponseEntity<>(consultaCepDTO, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/cep/{cep}")
	public ResponseEntity<Endereco> saveEnderecoFromApi(@PathVariable String cep) {
		return new ResponseEntity<>(enderecoService.saveEnderecoFromApi(cep), HttpStatus.CREATED);
	}

	@PostMapping
	public ResponseEntity<Endereco> saveEndereco(@RequestBody Endereco endereco) {
		return new ResponseEntity<>(enderecoService.saveEndereco(endereco), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Endereco> updateEndereco(@RequestBody Endereco endereco, @PathVariable Integer id) {
		return new ResponseEntity<>(enderecoService.updateEndereco(endereco, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Endereco> deleteEndereco(@PathVariable Integer id) {
		Endereco endereco = enderecoService.getEnderecoById(id);
		if (null == endereco)
			return new ResponseEntity<>(endereco, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(enderecoService.deleteEndereco(id), HttpStatus.OK);
	}

}
