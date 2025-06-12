package app.test.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import app.dto.AprimoramentoEntrada;
import app.entity.AprimoramentoArma;
import app.entity.AprimoramentoArma.Elemento;
import app.repository.AprimoramentoRepository;
import app.service.AprimoramentoService;
/*
@ExtendWith(MockitoExtension.class)
public class TestAprimoramentoServiceUnit{
	@Mock
	private AprimoramentoRepository aprimoramentoRepository;
	
	@InjectMocks
	private AprimoramentoService aprimoramentoService;
	
	@Test
	@DisplayName("Cena 00 - Teste Unitário: Deve criar Aprimoramento com elemento válido e nível ajustado")
	void criarAprimoramentoElementoValido() {
		//Arrange;
		AprimoramentoEntrada entrada = new AprimoramentoEntrada();
		entrada.setTipoEquipamento("espada");
		entrada.setTipoDanoOuDefesa("corte");
		entrada.setValorBase(100);
		entrada.setElemento("fogo");
		entrada.setNivelElemento(12);//acima do limite permitido;
		entrada.setAprimoramentoPricipal("arma longa");
		
		AprimoramentoArma esperado = new AprimoramentoArma();
		esperado.setElemento(Elemento.FOGO);
		esperado.setNivelElemento(10);
		
		when(aprimoramentoRepository.save(any(AprimoramentoArma.class)))
			.thenReturn(esperado);
		
		//Act;
		AprimoramentoArma resultado = aprimoramentoService.aprimorar(entrada);
		
		//Assert;
		ArgumentCaptor<AprimoramentoArma> captor = ArgumentCaptor.forClass(AprimoramentoArma.class);
		verify(aprimoramentoRepository).save(captor.capture());
		
		AprimoramentoArma salvo = captor.getValue();
		assertThat(salvo.getElemento()).isEqualTo(Elemento.FOGO);
		assertThat(salvo.getNivelElemento()).isEqualTo(10); //nível máximo;
		
		assertThat(resultado).isNotNull();
	}
	
	@Test
	@DisplayName("Cena 01 - Teste Unitário: deve lançar exceção para elemento inválido")
	void lancarExcecaoElementoInvalido() {
		//Arrange;
		AprimoramentoEntrada entrada = new AprimoramentoEntrada();
		entrada.setTipoEquipamento("armadura");
		entrada.setTipoDanoOuDefesa("fisico");
		entrada.setValorBase(80);
		entrada.setElemento("desconhecido");
		entrada.setNivelElemento(3);
		entrada.setAprimoramentoPricipal("defesa de titânio");
		
		//Act & Assert;
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> aprimoramentoService.aprimorar(entrada)
		);
		assertThat(exception.getMessage()).contains("Elemento Inválido");
	}
	
	@Test
	@DisplayName("Cena 02 - Teste Unitário: Deve criar Aprimoramento sem elemento (campo opcional)")
	void criarAprimoramentoSemElemento() {
		//Arrange;
		AprimoramentoEntrada entrada = new AprimoramentoEntrada();
		entrada.setTipoEquipamento("lança");
		entrada.setTipoDanoOuDefesa("perfurante");
		entrada.setValorBase(90);
		entrada.setElemento(""); //Vazio;
		entrada.setNivelElemento(0); //deve cair pra 1;
		entrada.setAprimoramentoPricipal("arma longa");
		
		AprimoramentoArma esperado = new AprimoramentoArma();
		esperado.setElemento(null);
		esperado.setNivelElemento(1);
		
		when(aprimoramentoRepository.save(any(AprimoramentoArma.class))).thenReturn(esperado);
		
		//Act;
		AprimoramentoArma resultado = aprimoramentoService.aprimorar(entrada);
		
		//Assert;
		assertThat(resultado.getElemento()).isNull();
		assertThat(resultado.getNivelElemento()).isEqualTo(1);
		assertThat(resultado).isInstanceOf(AprimoramentoArma.class);
	}
	
	@Test
	@DisplayName("Cena 03 - Teste Unitário: tipo de arma válido retorna mensagem formatada")
	void tipoEquipamentoComArmaValida() {
		String resposta = aprimoramentoService.tipoEquipamento("espada");
		assertThat(resposta).isEqualTo("Você escolheu aprimorar uma arma do tipo: espada.");
	}
	
	@Test
	@DisplayName("Cena 04 - Teste Unitário: tipo 'armadura' retorna mensagem específica")
	void tipoEquipamentoComArmadura() {
		String resposta = aprimoramentoService.tipoEquipamento("armadura");
		assertTrue(resposta.equalsIgnoreCase("Você escolheu aprimorar uma armadura."));
	}
	
	@Test
	@DisplayName("Cena 05 - Teste Unitário: tipo nulo ou vazio retorna aviso de não específicado")
	void tipoEquipamentoVazioOuNulo() {
		assertThat(aprimoramentoService.tipoEquipamento(null)).isEqualTo("Tipo de equipamento não específicado.");
		assertThat(aprimoramentoService.tipoEquipamento(" ")).isEqualTo("Tipo de equipamento não específicado.");
	}
	
	@Test
    @DisplayName("Cena 06 - Teste Unitário: tipo inválido retorna mensagem de erro")
	void tipoEquipamentoInvalido() {
		String resposta = aprimoramentoService.tipoEquipamento("martelo");
		assertThat(resposta).startsWith("Tipo de equipamento inválido.");
		assertTrue(resposta.contains("Tipo de equipamento inválido."));
	}
	
	@Test
	@DisplayName("Cena 07 - Teste Unitário: Deve retornar bônus de defesa para equipamento do tipo armadura")
	void tipoDanoOuDefesaComArmadura() {
		//Arrange;
		String tipoEquipamento = "armadura";
		String tipoDanoInicial = "C"; //não importa nesse caso;
		int valor = 15;
		
		//Act;
		String resultado = aprimoramentoService.tipoDanoOuDefesa(tipoEquipamento, tipoDanoInicial, valor);
		
		//Assert;
		assertThat(resultado).isEqualTo("Armadura com bônus de defesa de 15 pontos (CA estilo RPG)");
	}
	
	@Test
	@DisplayName("Cena 08 - Teste Unitário: Deve retornar descrição correta para dano do tipo perfuração")
	void tipoDanoOuDefesaComArmaPerfurante() {
		//Arrange;
		String tipoEquipamento = "espada";
		String tipoDanoInicial = "P";
		int valor = 20;
		
		//Act;
		String resultado = aprimoramentoService.tipoDanoOuDefesa(tipoEquipamento, tipoDanoInicial, valor);
		
		//Assert;
		assertThat(resultado).isEqualTo("Arma com dano do tipo Perfuração causando 20 pontos de dano.");
	}
}
*/
/*

*/