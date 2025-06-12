package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.dto.AprimoramentoEntrada;
import app.entity.AprimoramentoArma;
import app.entity.AprimoramentoArma.Elemento;
import app.service.AprimoramentoService;

@RestController
@RequestMapping("/api/aprimoramento")
@CrossOrigin("*")
public class AprimoramentoController{
	private final AprimoramentoService aprimoramentoService;
	
	@Autowired
	public AprimoramentoController(AprimoramentoService aprimoramentoService) {
		this.aprimoramentoService = aprimoramentoService;
	}
	
	//POST - Salva o aprimoramento com base nos dados de entrada;
	@PostMapping
	public ResponseEntity<AprimoramentoArma> aprimorar(@RequestBody AprimoramentoEntrada entrada){
		AprimoramentoArma aprimorando = aprimoramentoService.aprimorar(entrada);
		return ResponseEntity.ok(aprimorando);
	}
	
	//GWT - Determina o tipo de equipamento;
	@GetMapping("/tipoEquimento")
	public ResponseEntity<String> obterTipoEquipamento(@RequestParam String entrada){
		String resultado = aprimoramentoService.tipoEquipamento(entrada);
		return ResponseEntity.ok(resultado);
	}
	
	//GET - Determina o tipo de dano ou defesa baseado nos parâmetros;
	@GetMapping("/tipoDanoOuDefesa")
	public ResponseEntity<String> obterTipoDanoOuDefesa(@RequestParam String tipoEquipamento, @RequestParam String tipoDanoInicial, @RequestParam int valor){
		String resultado = aprimoramentoService.tipoDanoOuDefesa(tipoEquipamento, tipoDanoInicial, valor);
		return ResponseEntity.ok(resultado);
	}
	
	//GET - Apenas o retorno da confirmação do valor base;
	@GetMapping("/valorBase")
	public ResponseEntity<String> valorBase(@RequestParam String tipoEquipamento, @RequestParam int valorBase){
		return ResponseEntity.ok("Equipamento: " + tipoEquipamento + ", vaor base: " + valorBase);
	}
	
	//GET - Aplica o aprimoramento e salva no banco com bônus elemental;
	@GetMapping("/elementoBonuseAprimoramento")
	public ResponseEntity<String> aplicarAprimoramentoComBonus(@RequestParam String tipoEquipamento, @RequestParam String elemento, @RequestParam(defaultValue = "1") int nivel){
		try {
			Elemento elementoEnum = Elemento.valueOf(elemento.toUpperCase());
			AprimoramentoEntrada entrada = new AprimoramentoEntrada();
			entrada.setTipoEquipamento(tipoEquipamento);
			entrada.setElemento(elemento);
			entrada.setNivelElemento(nivel);
			
			AprimoramentoArma aprimorar = aprimoramentoService.aprimorar(entrada);
			String bonus = aprimoramentoService.calcularBonusElemental(tipoEquipamento, elementoEnum, nivel);
			
			return ResponseEntity.ok("Aprimoramento salvo com sucesso. Bônus: " + bonus);
		}catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Elemento inválido. Use: FOGO, TERRA, ÁGUA, VENTO, RELÂMPAGO, LUZ OU SOMBRAS.");
		}
	}
	
	//GET - Retorna o aprimoramento pricipal com base na entrada;
	@GetMapping("/aprimoramentoPrincipal")
	public ResponseEntity<String> obterAprimoramentoPrincipal(@RequestParam String tipoEquipamento, @RequestParam String aprimoramentoEscolhido, @RequestParam(defaultValue = "1") int nivel){
		try {
			String resultado = aprimoramentoService.aprimoramentoPrincipal(tipoEquipamento, aprimoramentoEscolhido, nivel, null);
			return ResponseEntity.ok(resultado);
		}catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//GET - Busca aprimoramentos por nome (nome parcial ou completo);
	@GetMapping("/buscar")
	public ResponseEntity <List<AprimoramentoArma>> buscarPorNome(@RequestParam("nome") String nome){
		List<AprimoramentoArma> resultados = aprimoramentoService.buscarPorNome(nome);
		return ResponseEntity.ok(resultados);
	}
//
	// GET - Retorna habilidade de arma com base no ID
	@GetMapping("/habilidadeArma")
	public ResponseEntity<AprimoramentoArma> habilidadeArma(@RequestParam Long id) {
	    try {
	        AprimoramentoArma resultado = aprimoramentoService.habilidadeArma(id);
	        return ResponseEntity.ok(resultado);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().build();
	    }
	}
	
	// GET - Retorna habilidade de armadura com base no ID
	@GetMapping("/habilidadeArmadura")
	public ResponseEntity<AprimoramentoArma> habilidadeArmadura(@RequestParam Long id) {
	    try {
	        AprimoramentoArma resultado = aprimoramentoService.habilidadeArmadura(id);
	        return ResponseEntity.ok(resultado);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().build();
	    }
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") long id){
		String mensagem = this.aprimoramentoService.deleteById(id);
		return new ResponseEntity<>(mensagem, HttpStatus.OK);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<AprimoramentoArma> findById(@PathVariable("id") long id){
		AprimoramentoArma aprimoramento = this.aprimoramentoService.findById(id);
		return new ResponseEntity<>(aprimoramento, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody AprimoramentoArma aprimoramento){
		String mensagem = this.aprimoramentoService.save(aprimoramento);
		return new ResponseEntity<>(mensagem, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody AprimoramentoArma aprimoramento, @PathVariable("id") long id){
		String mensagem = this.aprimoramentoService.update(aprimoramento, id);
		return new ResponseEntity<>(mensagem, HttpStatus.OK);
	}
}
/*

*/