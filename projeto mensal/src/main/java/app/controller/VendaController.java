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

import app.entity.Venda;
import app.service.VendaService;


@RestController
@RequestMapping("/api/venda")
public class VendaController {
	@Autowired
	private VendaService vendaService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Venda venda) {	
		try {	
			String mensagem = this.vendaService.save(venda);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);		
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro encontrado aqui!: "+e.getMessage(), HttpStatus.BAD_REQUEST);	
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Venda venda, @PathVariable int id) {	
		try {
			String mensagem = this.vendaService.update(id, venda);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);		
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro encontrado aqui!: "+e.getMessage(), HttpStatus.BAD_REQUEST);	
		}
	}

	@GetMapping("/listAll")
	public ResponseEntity<List<Venda>> listAll (){
		try {
			List<Venda> lista = this.vendaService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.CREATED);		
		} catch (Exception e) {	
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{idVenda}")
	public ResponseEntity<Venda> findById(@PathVariable long idVenda){	
		try {	
			Venda venda = this.vendaService.findById(idVenda);
			return new ResponseEntity<>(venda, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{idVenda}")
	public ResponseEntity<String> delete(@PathVariable long idVenda){
		try{
			String menssagem = this.vendaService.delete(idVenda);
			return new ResponseEntity<>(menssagem, HttpStatus.CREATED);
		} catch(Exception e){
			return new ResponseEntity<String>("Erro encontrado aqui!: "+e.getMessage(), HttpStatus.BAD_REQUEST); 
		}
	}

	@GetMapping("/findByEnderecoEntrega")
	public ResponseEntity<List<Venda>> findByEnderecoEntrega (@RequestParam String enderecoentrega){
		try {
			List<Venda> lista = this.vendaService.findByEnderecoEntrega(enderecoentrega);
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {	
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findByValorTotal")
	public ResponseEntity<List<Venda>> findByValorTotal (@RequestParam Double valortotal){
		try {
			List<Venda> lista = this.vendaService.findByValorTotal(valortotal);
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {	
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/buscarVendaEnderecoEntrega")
	public ResponseEntity<List<Venda>> buscarVendaEnderecoEntrega(@RequestParam String enderecoentrega){
		try {
			List<Venda> lista = this.vendaService.buscarVendaEnderecoEntrega(enderecoentrega);
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
