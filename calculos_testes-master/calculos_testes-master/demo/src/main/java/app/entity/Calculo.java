package app.entity;

import java.util.List;

import jakarta.persistence.Entity;
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
public class Calculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private List<Integer> lista;
	private int soma;
	private double media;
	private double mediana;
	private int maiorNumero;
	private int menorNumero;
	private int totalElementos;
	private List<Integer> moda;
	private double desvioPadrao;
	private int amplitude;
	
	
	
}
