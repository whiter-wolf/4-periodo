package app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AprimoramentoEntrada{
	private String tipoEquipamento;
	private String tipoDanoOuDefesa;
	private int valorBase;
	private String elemento;//Ex: FOGO, ÁGUA, etc;
	private String aprimoramentoPricipal;
	private int nivelElemento;
	private int nivelHabilidadeArma;
	private int nivelHabilidadeArmadura;
	
	//Apenas métodos utilitários para o frontend ou logs;
	public boolean isArmadura() {
		return tipoEquipamento != null && tipoEquipamento.equalsIgnoreCase("armadura");
	}
}
/*

*/