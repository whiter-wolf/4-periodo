package app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AprimoramentoArma {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Tipo de equipamento: espada, lança, pistola, rifle, lâmina, armadura, etc;
	private String tipoEquipamento;
	
	//Tipo de dano: corte, perfuração, balístico, impacto ou bônus de defesa se for armadura;
	private String tipoDanoOuDefesa;
	
	//Valor numérico do dano ou bônus de defesa;
	private int valorBase;
	
	//Elemento aplicado (opcional): fogo, terra, água, vento, relâmpago, luz ou sombras;
	@Enumerated(EnumType.STRING)
	private Elemento elemento;
	
	//Aprimoramento principal obrigatório;
	private String aprimoramentoPrincipal;
	
	//Nível do aprimoramento elemental (de 1 a 10);
	private int nivelElemento;
	
	//Nível do aprimoramento da habilidade de arma (de 1 a 10);
	private int nivelHabilidadeArma;
	
	//Nível do aprimoramento da habilidade de armadura (de 1 a 10);
    private int nivelHabilidadeArmadura;
	
	public enum Elemento{
		FOGO, TERRA, ÁGUA, VENTO, RELÂMPAGO, LUZ, SOMBRAS
	}
	
	//Set com a valídação do nível elemental;
	public void setNivelElementos(int nivelElemento) {
		this.nivelElemento = Math.min(Math.max(nivelElemento, 1), 10);
	}
	
	//Set com a validação do nível da habilidade de arma;
	public void setNivelHabilidadeArma(int nivelHabilidadeArma) {
		this.nivelHabilidadeArma = Math.min(Math.max(nivelHabilidadeArma, 1), 10);
	}
	
	//Set com a validação do nível da habilidade de armadura;
	public void setNivelHabilidadeArmadura(int nivelHabilidadeArmadura) {
		this.nivelHabilidadeArmadura = Math.min(Math.max(nivelHabilidadeArmadura, 1), 10);
	}
	
	public String getElementoCouter() {
		if(elemento == null) return "Nenhum elemento";
		return switch(elemento) {
			case FOGO -> "Fraco contra ÁGUA, forte contra TERRA";
			case TERRA -> "Fraco contra FOGO, forte contra LUZ";
			case ÁGUA -> "Fraco contra RELÂMPAGO, forte contra FOGO";
			case VENTO -> "Fraco contra TERRA, forte contra RELÂMPAGO";
			case RELÂMPAGO -> "Fraco contra TERRA, forte contra SOMBRAS";
			case LUZ -> "Contrapõe a SOMBRAS";
			case SOMBRAS -> "Contrapõe a LUZ";
		};
	}
	
	public boolean isArmadura() {
		return tipoEquipamento != null && tipoEquipamento.equalsIgnoreCase("armadura");
	}
	
	public String descricaoCompleta() {
		String base = isArmadura()
				? "Armadura tipo " + tipoEquipamento + " com defesa base de " + valorBase
				: "Arma do tipo " + tipoEquipamento + " com dano de " + valorBase + " (" + tipoDanoOuDefesa + ")";
		String elem = (elemento != null) ? " com elemento " + elemento.name() : "";
		String extra = (aprimoramentoPrincipal != null && !aprimoramentoPrincipal.isEmpty())
				? " e melhoria: " + aprimoramentoPrincipal : "";
		return base + elem + extra;
	}
	
	//Lógica de negócio para aumentar o nível da habilidade de arma;
	private int nivelAprimoramentoArma() {
		return Math.min(nivelHabilidadeArma + 1, 10);
	}
	
	//Lógica de negócio para aumentar o nível da habilidade de armadura;
	private int nivelAprimoramentoArmadura() {
		return Math.min(nivelHabilidadeArmadura + 1, 10);
	}
	
	//Métodos para atualizar os níveis;
	public void aprimorarHabilidadeArma() {
		this.nivelHabilidadeArma = nivelAprimoramentoArma();
	}
	
	public void aprimorarHabilidadeArmadura() {
		this.nivelHabilidadeArmadura = nivelAprimoramentoArmadura();
	}
	
	public void setNivelElemento(int nivelElemento) {
		this.nivelElemento = Math.min(Math.max(nivelElemento, 1), 10);//Entre 1 e 10;
	}
}
/*

*/