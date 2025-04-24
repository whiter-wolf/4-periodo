package app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.Entrada;
import app.entity.Calculo;
import app.repository.CalculoRepository;

@Service
public class CalculoService {

	@Autowired
	private CalculoRepository calculoRepository;

	public Calculo calcular(Entrada entrada) {

		Calculo calculo = new Calculo();
		calculo.setLista(entrada.getLista());
		calculo.setSoma(this.soma(entrada.getLista()));
		calculo.setMedia(this.media(entrada.getLista()));
		calculo.setMediana(this.mediana(entrada.getLista()));
		calculo.setMaiorNumero(this.maiorNumero(entrada.getLista()));
		calculo.setMenorNumero(this.menorNumero(entrada.getLista()));
		calculo.setTotalElementos(this.totalElementos(entrada.getLista()));
		calculo.setModa(this.moda(entrada.getLista()));
		calculo.setDesvioPadrao(this.desvioPadrao(entrada.getLista()));
		calculo.setAmplitude(this.amplitude(entrada.getLista()));

		this.calculoRepository.save(calculo);
		return calculo;

	}


	public int soma(List<Integer> lista) {
		int soma = 0;
		for (int i = 0; i < lista.size(); i++) {
			if(lista.get(i) == null)
				throw new RuntimeException("dslçfjakd");
			else
				soma += lista.get(i);
		}
		return soma;
	}


	public double media(List<Integer> lista) {
		return (double) this.soma(lista) / lista.size();
	}


	public double mediana(List<Integer> lista) {
		if (lista == null || lista.isEmpty()) {
	        throw new IllegalArgumentException("A lista não pode ser nula ou vazia");
	    }
		
		Collections.sort(lista);

	    if (lista.size() % 2 == 1) { //ÍMPAR
	        return lista.get(lista.size() / 2);
	    } else {
	        int meio1 = lista.get(lista.size() / 2 - 1);
	        int meio2 = lista.get(lista.size() / 2);
	        return (meio1 + meio2) / 2.0;
	    }
	}

	public int maiorNumero(List <Integer> lista) {
		int aux = lista.get(0);
		for(int i = 0; i < lista.size(); i++) {
			if(aux < lista.get(i))
				aux = lista.get(i);
		}
		return aux;
	}
	
	public int menorNumero(List <Integer> lista) {
		int aux = lista.get(0);
		for(int i = 0; i < lista.size(); i++) {
			if(aux > lista.get(i))
				aux = lista.get(i);
		}
		return aux;
	}
	
	public int totalElementos(List<Integer> lista) {
		if(lista == null) {
			throw new IllegalArgumentException("Lista não pode ser nula");
		}
		return lista.size();
	}
	
	public List<Integer> moda(List<Integer> lista){
		if(lista == null || lista.isEmpty()){
			throw new IllegalArgumentException("A lista não pode ser nula ou vazia");
		}
		
		List<Integer> modas = new ArrayList<>();
		List<Integer> unicos = new ArrayList<>();
		List<Integer> frequencias = new ArrayList<>();
		
		for(Integer num : lista) {
			if(num == null) continue; //ignora os nulos se necessário;
			if(!unicos.contains(num)) {
				unicos.add(num);
				frequencias.add(1);
			} else {
				int index = unicos.indexOf(num);
				frequencias.set(index, frequencias.get(index) + 1);
			}
		}
		
		int maxFreq = Collections.max(frequencias);
		
		for(int i = 0; i < unicos.size(); i++) {
			if(frequencias.get(i) == maxFreq) {
				modas.add(unicos.get(i));
			}
		}
		
		return modas;
	}
	
	public double desvioPadrao(List<Integer> lista) {
		if(lista == null || lista.isEmpty()) {
			throw new IllegalArgumentException("A lista não pode ser nula ou vazia");
		}
		double media = this.media(lista);
		double somaQuadrados = 0.0;
		for(Integer num : lista) {
			somaQuadrados += Math.pow(num - media, 2);
		}
		return Math.sqrt(somaQuadrados / lista.size());
	}
	
	public int amplitude(List<Integer> lista) {
		return maiorNumero(lista) - menorNumero(lista);
	}
}
