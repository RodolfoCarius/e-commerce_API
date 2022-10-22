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

import br.com.residencia.ecommerce.dto.ItemPedidoDTO;
import br.com.residencia.ecommerce.entity.ItemPedido;
import br.com.residencia.ecommerce.exception.NoSuchElementFoundException;
import br.com.residencia.ecommerce.service.ItemPedidoService;

@RestController
@RequestMapping("/itensPedidos")
public class ItemPedidoController {
	@Autowired
	ItemPedidoService itemPedidoService;

	@GetMapping
	public ResponseEntity<List<ItemPedido>> getAllItensPedidos() {
		return new ResponseEntity<>(itemPedidoService.getAllItensPedidos(), HttpStatus.OK);
	}

	@GetMapping("/dto")
	public ResponseEntity<List<ItemPedidoDTO>> getAllItensProdutosDTO() {
		return new ResponseEntity<>(itemPedidoService.getAllItensPedidosDTO(), HttpStatus.OK);
	}

	/*
	 * @GetMapping("/{id}") public ResponseEntity<ItemPedido>
	 * getItemPedidoById(@PathVariable Integer id) { ItemPedido itemPedido =
	 * itemPedidoService.getItemPedidoById(id); if(null != itemPedido) return new
	 * ResponseEntity<>(itemPedido, HttpStatus.OK); else return new
	 * ResponseEntity<>(itemPedido, HttpStatus.NOT_FOUND); }
	 */

	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> getItemPedidoById(@PathVariable Integer id) {
		ItemPedido itemPedido = new ItemPedido();

		try {
			itemPedido = itemPedidoService.getItemPedidoById(id);
			return new ResponseEntity<>(itemPedido, HttpStatus.OK);
		} catch (Exception ex) {
			throw new NoSuchElementFoundException("Não foi encontrado resultado com o id " + id);
		}
	}

	@PostMapping
	public ResponseEntity<ItemPedido> saveItemPedido(@RequestBody ItemPedido itemPedido) {
		return new ResponseEntity<>(itemPedidoService.saveItemPedido(itemPedido), HttpStatus.CREATED);
	}

	@PostMapping("/dto")
	public ResponseEntity<ItemPedidoDTO> saveItemPedidoDTO(@RequestBody ItemPedidoDTO itemPedidoDTO) {
		return new ResponseEntity<>(itemPedidoService.saveItemPedidoDTO(itemPedidoDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemPedido> updateItemPedido(@RequestBody ItemPedido itemPedido, @PathVariable Integer id) {
		return new ResponseEntity<>(itemPedidoService.updateItemPedido(itemPedido, id), HttpStatus.OK);
	}

	@PutMapping("/dto/{id}")
	public ResponseEntity<ItemPedidoDTO> updateItemPedidoDTO(@RequestBody ItemPedidoDTO itemPedidoDTO,
			@PathVariable Integer id) {
		return new ResponseEntity<>(itemPedidoService.updateItemPedidoDTO(itemPedidoDTO, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ItemPedido> deleteItemPedido(@PathVariable Integer id) {
		ItemPedido itemPedido = itemPedidoService.getItemPedidoById(id);
		if (null == itemPedido)
			return new ResponseEntity<>(itemPedido, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(itemPedidoService.deleteItemPedido(id), HttpStatus.OK);
	}

}
