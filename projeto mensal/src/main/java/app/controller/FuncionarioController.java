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

import app.entity.Funcionario;
import app.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {
	@Autowired
	private FuncionarioService funcionarioService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Funcionario funcionario){
		try {
			String menssagem = this.funcionarioService.save(funcionario);
			return new ResponseEntity<String>(menssagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro encontrado aqui! :"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Funcionario funcionario, @PathVariable int id){
		try {
			String menssagem = this.funcionarioService.update(id, funcionario);
			return new ResponseEntity<String>(menssagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro encontrado aqui! :"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<List<Funcionario>> listAll(){
		try {
			List<Funcionario> lista = this.funcionarioService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{idFuncionario}")
	public ResponseEntity<Funcionario> findById(@PathVariable long idFuncionario){
		try {
			Funcionario funcionario = this.funcionarioService.findById(idFuncionario);
			return new ResponseEntity<>(funcionario, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idFuncionario}")
	public ResponseEntity<String> delete(@PathVariable long idFuncionario){
		try {
			String menssagem = this.funcionarioService.delete(idFuncionario);
			return new ResponseEntity<>(menssagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro encontrado aqui! :"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByNome")
	public ResponseEntity<List<Funcionario>> findByNome(@RequestParam String nome){
		try {
			List<Funcionario> lista = this.funcionarioService.findByNome(nome);
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByIdade")
	public ResponseEntity<List<Funcionario>> findByIdade(@RequestParam Integer idade){
		try {
			List<Funcionario> lista = this.funcionarioService.findByIdade(idade);
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByMatricula")
	public ResponseEntity<List<Funcionario>> findByMatricula(@RequestParam String matricula){
		try {
			List<Funcionario> lista = this.funcionarioService.findByMatricula(matricula);
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/buscarFuncionarioNome")
	public ResponseEntity<List<Funcionario>> buscarFuncionarioNome(@RequestParam String nome){
		try {
			List<Funcionario> lista = this.funcionarioService.buscarFuncionarioNome(nome);
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}