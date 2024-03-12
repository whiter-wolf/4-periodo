package app.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Cliente;
import app.service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Cliente cliente){
		try {
			String menssagem = this.clienteService.save(cliente);
			return new ResponseEntity<String>(menssagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro encontrado aqui! :"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Cliente cliente, @PathVariable int id){
		try {
			String menssagem = this.clienteService.update(id, cliente);
			return new ResponseEntity<String>(menssagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro encontrado aqui! :"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<List<Cliente>> listAll(){
		try {
			List<Cliente> lista = this.clienteService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{idCliente}")
	public ResponseEntity<Cliente> findById(@PathVariable long idCliente){
		try {
			Cliente cliente = this.clienteService.findById(idCliente);
			return new ResponseEntity<>(cliente, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idCliente}")
	public ResponseEntity<String> delete(@PathVariable long idCliente){
		try {
			String menssagem = this.clienteService.delete(idCliente);
			return new ResponseEntity<>(menssagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro encontrado aqui! :"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByNome")
	public ResponseEntity<List<Cliente>> findByNome(@RequestParam String nome){
		try {
			List<Cliente> lista = this.clienteService.findByNome(nome);
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByCpf")
	public ResponseEntity<List<Cliente>> findByCpf(@RequestParam String cpf){
		try {
			List<Cliente> lista = this.clienteService.findByCpf(cpf);
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByIdade")
	public ResponseEntity<List<Cliente>> findByIdade(@RequestParam Integer idade){
		try {
			List<Cliente> lista = this.clienteService.findByIdade(idade);
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByTelefone")
	public ResponseEntity<List<Cliente>> findByTelefone(@RequestParam String telefone){
		try {
			List<Cliente> lista = this.clienteService.findByTelefone(telefone);
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/buscarClienteNome")
	public ResponseEntity<List<Cliente>> buscarClienteNome(@RequestParam String nome){
		try {
			List<Cliente> lista = this.clienteService.buscarClienteNome(nome);
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}