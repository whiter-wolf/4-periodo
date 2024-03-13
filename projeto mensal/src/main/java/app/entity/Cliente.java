package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "cliente")
	@JsonIgnoreProperties("cliente")
	private List<Venda> vendas;
}