package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	public List<Cliente> findByNome(String nome);
	
	public List<Cliente> findByCpf(String cpf);
	
	public List<Cliente> findByIdade(Integer idade);
	
	public List<Cliente> findByTelefone(String telefone);
	
	@Query("SELECT e FROM Cliente e WHERE e.nome LIKE %:nome%")
	public List<Cliente> buscarParteNome(String nome);
	
	@Query("FROM Cliente c WHERE c.nome > :nome")
	public List<Cliente> buscarClienteNome(String nome);
}