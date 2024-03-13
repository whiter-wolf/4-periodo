package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{
	public List<Venda> findByEnderecoEntrega(String enderecoentrega);
	
	public List<Venda> findByValorTotal(Double valortotal);
	
	@Query("SELECT e FROM Venda e WHERE e.enderecoEntrega LIKE %:enderecoEntrega%")
	public List<Venda> buscarParteEndereco(String enderecoEntrega);
	
	@Query("FROM Venda c WHERE c.enderecoEntrega > :enderecoEntrega")
	public List<Venda> buscarVendaEnderecoEntrega(String enderecoEntrega);
}