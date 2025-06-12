package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.AprimoramentoEntrada;
import app.entity.AprimoramentoArma;
import app.entity.AprimoramentoArma.Elemento;
import app.repository.AprimoramentoRepository;

@Service
public class AprimoramentoService{
	@Autowired
	private AprimoramentoRepository aprimoramentoRepository;
	
	public AprimoramentoArma aprimorar(AprimoramentoEntrada entrada) {
		validarEntrada(entrada);
		
		AprimoramentoArma aprimoramento = new AprimoramentoArma();
		aprimoramento.setTipoEquipamento(entrada.getTipoEquipamento());
		aprimoramento.setTipoDanoOuDefesa(entrada.getTipoDanoOuDefesa());
		aprimoramento.setValorBase(entrada.getValorBase());
		
		if(entrada.getElemento() != null && !entrada.getElemento().isEmpty()) {
			try {
				Elemento elementoEnum = Elemento.valueOf(entrada.getElemento().toUpperCase());
				aprimoramento.setElemento(elementoEnum);
			}catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Elemento inválido: " + entrada.getElemento());
			}
		}else {
			aprimoramento.setElemento(null);//Elemento é opcional;
		}
		
		int nivel = entrada.getNivelElemento() > 0 ? entrada.getNivelElemento() : 1;
		aprimoramento.setNivelElemento(Math.min(nivel, 10));//Garante que o nível fique entre 1 e 10;
		
		aprimoramento.setAprimoramentoPrincipal(entrada.getAprimoramentoPricipal());
		
		return aprimoramentoRepository.save(aprimoramento);
	}
	
	private void validarEntrada(AprimoramentoEntrada entrada) {
		if(entrada.getTipoEquipamento() == null || entrada.getTipoEquipamento().trim().isEmpty()) {
			throw new IllegalArgumentException("Tipo de equipamento não informado.");
		}
		
		if(entrada.getValorBase() <= 0) {
			throw new IllegalArgumentException("O valor base deve ser maior que zero.");
		}
		
		String tipo = entrada.getTipoEquipamento().trim().toLowerCase();
		
		if(!tipo.equals("arma") && !tipo.equals("armadura")) {
			throw new IllegalArgumentException("Tipo de equipamento inválido. Use 'Arma' ou 'Armadura'.");
		}
	}
	
	public String tipoEquipamento(String entrada) {
		if(entrada == null || entrada.trim().isEmpty()) {
			return "Tipo de equipamento não especifícado.";
		}
		
		String tipo = entrada.trim().toLowerCase();
		
		return switch(tipo) {
			case "espada", "lança", "pistola", "rifle", "lâmina", "outro tipo de equipamento" ->
				"Você escolheu aprimorar uma arma do tipo: " + tipo.substring(0, 1).toUpperCase() + tipo.substring(1);
			case "armadura" -> "Você escolheu aprimorar uma armadura.";
			default -> "Tipo de equipamento inválido. Por favor, informe 'Espada', 'Lança', 'Pistola', 'Rifle', 'Lâmina', 'Armadura' ou 'Outro tipo de equipamento'.";
		};
	}

	public String tipoDanoOuDefesa(String tipoEquipamento, String tipoDanoInicial, int valor) {
		if(tipoEquipamento == null || tipoEquipamento.trim().isEmpty()) {
			return "Tipo de equipamento não informado.";
		}
		
		String tipo = tipoEquipamento.trim().toLowerCase();
		
		if(tipo.equals("armadura")) {
			return "Armadura com bônus de defesa de " + valor + " pontos (Ca estilo RPG).";
		}
		
		String tipoDano;
		switch(tipoDanoInicial.toUpperCase()) {
			case "C" -> tipoDano = "Corte";
			case "P" -> tipoDano = "Perfuração";
			case "B" -> tipoDano = "Balístico";
			case "I" -> tipoDano = "Impacto";
			default -> tipoDano = "Tipo de dano inválido. Use: C (Corte), P (Perfuração), B (Balístico) ou I (Impacto).";
		}
		
		if(tipoDano.startsWith("Tipo de dano inválido")) {
			return tipoDano;
		}
		
		return "Arma com dano do tipo " + tipoDano + " causando " + valor + " pontos de dano.";
	}
	
	public String calcularBonusElemental(String tipoEquipamento, Elemento elemento, int nivel) {
        if (elemento == null) return "Nenhum elemento selecionado";

        int bonus = Math.min(Math.max(nivel, 1), 10) * 5;

        if (tipoEquipamento.equalsIgnoreCase("armadura")) {
            return "Armadura recebe +" + bonus + " de resistência contra o elemento " + elemento.name();
        } else {
            return "Arma recebe +" + bonus + " de dano do elemento " + elemento.name();
        }
    }
	
	public String aprimoramentoPrincipal(String tipoEquipamento, String aprimoramentoEscolhido, int nivel, Elemento elemento) {
		if(tipoEquipamento == null || aprimoramentoEscolhido == null) {
			return "Tipo de equipamento ou aprimoramento não informado.";
		}
		
		if(nivel < 1 || nivel > 10) {
			return "Nível inválido. O nível deve estar entre 1 e 10.";
		}
		
		String tipo = tipoEquipamento.trim().toLowerCase();
		String aprimoramento = aprimoramentoEscolhido.trim().toLowerCase();
		int bonus = nivel * 5;
		
		if(tipo.equals("arma")) {
			return switch(aprimoramento) {
				case "Dano Aumentado" -> "Arma recebe +" + bonus + " de dano adicional (nível " + nivel + ").";
				case "Arma de Titânio" -> "Arma aprimorada com titânio, aumentando a sua resistência física.";
				case "Arma Longa" -> "Arma alongada: maior alcance e impacto, em armas de disparo como pistola e rifle o alcançe do disparo aumenta (ex: alcançe curto para alcançe médio).";
				case "Critico Máximo" -> "Chance de erro reduzida: os golpes se tornam mais precisos e mortais.";
				case "Arma Inteligente" -> "Arma com consciência: pode se mover sozinha enquanto o usuário estiver consiente.";
				case "Aprimoramento Elemental" ->{
					if(elemento == null) {
						yield "Elemento não especificado para o Aprimoramento Elemental.";
					}
					yield "Arma recebe aprimoramento elemental: " + calcularBonusElemental("Arma", elemento, nivel);
				}
				default -> "Aprimoramento inválido para a arma.";
			};
		}else if(tipo.equals("armadura")) {
			return switch(aprimoramento) {
				case "Casco" -> "Armadura equipada com escudo de energia contra ataques de curta e média distância.";
				case "Defesa de Titânio" -> "Armadura recebe +" + bonus + " de defesa (nível " + nivel + ").";
				case "Muralha" -> "Ao bloquear ataques, nenhum dano é recebido, mas a CA da armadura sofre desgaste proporsional.";
				case "Cobertura Rápida" -> "Armadura com sistema de alerta: reflexos aprimorados contra ataques, inclusive ataques surpresas.";
				case "Resistencia Elemental" -> {
					if(elemento == null) {
						yield "Elemento não especificado para a Resistencia Elemental.";
					}
					yield "Armadura recebe resistência elemental: " + calcularBonusElemental ("Armadura", elemento, nivel);
				}
				default -> "Aprimoramento inválido para a armadura.";
			};
		}else {
			return "Tipo de equipamento inválido. Use 'Arma' ou 'Armadura'.";
		}
	}
	
	public List<AprimoramentoArma> buscarPorNome(String nome){
		return aprimoramentoRepository.findByAprimoramentoPrincipalContainingIgnoreCase(nome);
	}

	public AprimoramentoArma habilidadeArma(Long id) {
		AprimoramentoArma aprimoramento = aprimoramentoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Aprimoramento não encontrado"));
		
		if(aprimoramento.isArmadura()) {
			throw new IllegalArgumentException("Este equipamento é uma armadura, naõ pe possível aplicar 'Dano Aumentado'");
		}
		
		aprimoramento.aprimorarHabilidadeArma();
		return aprimoramentoRepository.save(aprimoramento);
	}
	
	
	public AprimoramentoArma habilidadeArmadura(Long id) {
		AprimoramentoArma aprimoramento = aprimoramentoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Aprimoramento não encontrado"));
		
		if(!aprimoramento.isArmadura()) {
			throw new IllegalArgumentException("Este equipamento não é uma armadura, não é possível aplicar 'Defesa de Titânio'");
		}
		
		aprimoramento.aprimorarHabilidadeArmadura();
		return aprimoramentoRepository.save(aprimoramento);
	}
	
	
	public String deleteById(long id) {
		this.aprimoramentoRepository.deleteById(id);
		return "Aprimoramento deletado com sucesso";
	}
	
	public AprimoramentoArma findById(long id) {
		Optional<AprimoramentoArma> aprimoramento = this.aprimoramentoRepository.findById(id);
		if(aprimoramento.isPresent())
			return aprimoramento.get();
		else
			return null;
	}
	
	public String save (AprimoramentoArma aprimoramento) {
		this.aprimoramentoRepository.save(aprimoramento);
		return "Aprimoramento salvo com sucesso.";
	}
	
	public String update(AprimoramentoArma aprimoramento, long id) {
		aprimoramento.setId(id);
		this.aprimoramentoRepository.save(aprimoramento);
		return "Aprimoramento atualizado com sucesso.";
	}
}
/*

*/