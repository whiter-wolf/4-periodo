package app.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String enderecoentrega;
	
	@NotNull(message = "Este campo não pode ser nulo")
	private Double valortotal;
	
	@OneToMany(mappedBy = "venda")
	private List<Cliente> clientes;
	
	@OneToMany(mappedBy = "venda")
	private List<Funcionario> funcionarios;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "venda_produto")
	private List<Produto> produtos;
}