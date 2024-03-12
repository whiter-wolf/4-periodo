package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "Este campo não pode ser nulo")
	private String nome;
	
	@NotNull(message = "Este campo não pode ser nulo")
	private String cpf;
	
	@NotNull(message = "Este campo não pode ser nulo")
	private Integer idade;
	
	@NotNull(message = "Este campo não pode ser nulo")
	private String telefone;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("clientes")
	private Venda venda;
}