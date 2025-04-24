package app.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.service.CalculoService;

@SpringBootTest
public class CalculoServiceTest {
	
	@Autowired
	CalculoService calculoService;

	@Test
	@DisplayName("Cena 01 - Testando o método somar com valores válidos")
	void cenario01() {
		List<Integer> lista = new ArrayList<>();
		lista.add(3);
		lista.add(5);
		lista.add(2);
		
		int resultadoObtido = this.calculoService.soma(lista);
		
		assertEquals(10, resultadoObtido);
	}
	
	@Test
	@DisplayName("Cena 02 - Testando o método somar com valores inválidos")
	void cenario02() {
		List<Integer> lista = new ArrayList<>();
		lista.add(3);
		lista.add(null);
		lista.add(2);
				
		assertThrows(Exception.class,() -> {
			this.calculoService.soma(lista);
		});
		
	}
	
	@Test
	@DisplayName("Cena 03 - Testar mediana com número par de elementos")
	void cenario03() {
		List<Integer> lista = new ArrayList<>();
		lista.add(3);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		
		assertEquals(3, this.calculoService.mediana(lista));
	}
	
	@Test
	@DisplayName("Cena 04 - Testar mediana com número ímpar de elementos")
	void cenario04() {
		List<Integer> lista = new ArrayList<>();
		lista.add(3);
		lista.add(2);
		lista.add(1);
		lista.add(9);
		lista.add(4);
		
		assertEquals(3, this.calculoService.mediana(lista));
	}
	
	
	@Test
	@DisplayName("Cena 05 - Testar mediana com número ímpar de elementos")
	void cenario05() {
		List<Integer> lista = new ArrayList<>();
		lista.add(8);
		lista.add(2);
		lista.add(1);
		lista.add(9);
		lista.add(39);
		lista.add(339);
		lista.add(4);
		
		assertEquals(8, this.calculoService.mediana(lista));
	}
	
	@Test
	@DisplayName ("Cena 06 - Testar o maiorNumero em lista padrão")
	public void testMaiorNumeroListaPadrao() {
		List<Integer> lista = new ArrayList<>();
		lista.add(1);
		lista.add(3);
		lista.add(2);
		lista.add(8);
		lista.add(5);
		assertEquals(8, calculoService.maiorNumero(lista));
	}
	
	@Test
	@DisplayName("Cena 07 - Testar o maiorNumero em lista de números iguais")
	public void testMaiorNumeroTodosIguais() {
		List<Integer> lista = new ArrayList<>();
		lista.add(5);
		lista.add(5);
		lista.add(5);
		assertThat(calculoService.maiorNumero(lista)).isEqualTo(5);
	}
	
	@Test
	@DisplayName("Cena 8 - Testar o maiorNumero com números negativos")
	public void testMaiorNumeroNegativos() {
		List<Integer> lista = new ArrayList<>();
		lista.add(-10);
		lista.add(-20);
		lista.add(-3);
		assertThat(calculoService.maiorNumero(lista)).isEqualTo(-3);
	}
	
	@Test
	@DisplayName("Cena 09 - Testar o menorNumero em lista padrão")
	public void testMenorNumeroListaPadrao() {
		List<Integer> lista = new ArrayList<>();
		lista.add(10);
		lista.add(2);
		lista.add(7);
		lista.add(1);
		lista.add(5);
		assertEquals(1, calculoService.menorNumero(lista));
	}
	
	@Test
	@DisplayName("Cena 10 - Testar o menorNumero em lista de números iguais")
	public void testMenorNumeroTodosIguais() {
		List<Integer> lista = new ArrayList<>();
		lista.add(3);
		lista.add(3);
		lista.add(3);
		assertThat(calculoService.menorNumero(lista)).isEqualTo(3);
	}
	
	@Test
	@DisplayName("Cena 11 - Testar o menorNumero com zero na lista")
	public void testMenorNumeroComZero() {
		List<Integer> lista = new ArrayList<>();
		lista.add(0);
		lista.add(5);
		lista.add(10);
		lista.add(15);
		assertThat(calculoService.menorNumero(lista)).isEqualTo(0);
	}
	
	@Test
	@DisplayName("Cena 12 - Testar o totalElementos com a lista preenchida")
	public void testTotalElementosListaPreenchida() {
		List<Integer> lista = new ArrayList<>();
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		assertEquals(4, calculoService.totalElementos(lista));
	}
	
	@Test
	@DisplayName("Cena 13 - Testar o totalElementos com a lista vazia")
	public void testTotalElementosListaVazia() {
		List<Integer> lista = Collections.emptyList();
		assertThat(calculoService.totalElementos(lista)).isEqualTo(0);
	}
	
	@Test
	@DisplayName("Cena 14 - Testar o totalElementos com lista nula")
	public void testTotalElementosListaNula() {
		assertThrows(IllegalArgumentException.class, () -> calculoService.totalElementos(null));
	}
	
	@Test
	@DisplayName("Cena 15 - Testar o moda com valor único frequente")
	public void testModaUnicoValorFrequente() {
		List<Integer> lista = new ArrayList<>();
		lista.add(1);
		lista.add(2);
		lista.add(2);
		lista.add(3);
		List<Integer> resultado = calculoService.moda(lista);
		assertThat(resultado).containsExactly(2);
	}
	
	@Test
	@DisplayName("Cena 16 - Testar o moda com multiplos valores frequentes")
	public void testModaMultiplosValoresFrequentes() {
		List<Integer> lista = new ArrayList<>();
		lista.add(1);
		lista.add(1);
		lista.add(1);
		lista.add(2);
		lista.add(2);
		lista.add(3);
		List<Integer> resultado = calculoService.moda(lista);
		assertThat(resultado).containsExactlyInAnyOrder(1);
	}
	
	@Test
	@DisplayName("Cena 16B - Testar o moda com multiplos valores frequentes")
	public void testModaMultiplosValoresFrequentesB() {
		List<Integer> lista = new ArrayList<>();
		lista.add(1);
		lista.add(1);
		lista.add(2);
		lista.add(2);
		lista.add(3);
		
		List<Integer> resultado = calculoService.moda(lista);
		Collections.sort(resultado);
		assertEquals(resultado.get(0), 1);
		assertEquals(resultado.get(1), 2);
	}
	
	@Test
	@DisplayName("Cena 17 - Testar o moda com lista nula")
	public void testModaComListaNula() {
		assertThrows(IllegalArgumentException.class,() -> calculoService.moda(null));
	}
	
	@Test
	@DisplayName("Cena 18 - Testar o moda com a lista vazia")
	public void testModaComListaVazia() {
		assertThrows(IllegalArgumentException.class, () -> calculoService.moda(Collections.emptyList()));
	}
	
	@Test
	@DisplayName("Cena 19 - Testar o desvioPadrao com lista basica")
	public void testDesvioPadraoBasico() {
		List<Integer> lista = new ArrayList<>();
		lista.add(2);
		lista.add(4);
		lista.add(4);
		lista.add(4);
		lista.add(5);
		lista.add(5);
		lista.add(7);
		lista.add(9);
		double desvio = calculoService.desvioPadrao(lista);
		assertThat(desvio).isCloseTo(2.0, within(0.01));
	}
	
	@Test
	@DisplayName("Cena 20 - Testar o desvioPadrao com os números iguais")
	public void testDesvioPadraoComTodosIguais() {
		List<Integer> lista = new ArrayList<>();
		lista.add(3);
		lista.add(3);
		lista.add(3);
		lista.add(3);
		assertEquals(0.0, calculoService.desvioPadrao(lista));
	}
	
	@Test
	@DisplayName("Cena 21 - Testar o desvioPadrao com lista nula")
	public void testDesvioPadraoComListaNula() {
		assertThrows(IllegalArgumentException.class, () -> calculoService.desvioPadrao(null));
	}
	
	@Test
	@DisplayName("Cena 22 - Testar o amplitude com valores positivos")
	public void testAmplitudeComValoresPositivos() {
		List<Integer> lista = new ArrayList<>();
		lista.add(5);
		lista.add(10);
		lista.add(15);
		assertThat(calculoService.amplitude(lista)).isEqualTo(10);
	}
	
	@Test
	@DisplayName("Cena 23 - Testar o amplitude com valores negativos")
	public void testAmplitudeComNegativos() {
		List<Integer> lista = new ArrayList<>();
		lista.add(-10);
		lista.add(-5);
		lista.add(-1);
		assertEquals(9, calculoService.amplitude(lista));
	}
	
	@Test
	@DisplayName("Cena 24 - Testar o amplitude com todos os números iguais")
	public void testAmplitudeTodosIguais() {
		List<Integer> lista = new ArrayList<>();
		lista.add(3);
		lista.add(3);
		lista.add(3);
		assertThat(calculoService.amplitude(lista)).isEqualTo(0);
	}
}
