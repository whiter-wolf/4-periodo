package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{
	public List<Venda> findByEnderecoEntrega(String enderecoentrega);
	
	public List<Venda> findByParteEndereco(String enderecoentrega);
	
	public List<Venda> findByValorTotal(Double valortotal);
	
	public List<Venda> findByVenda(Venda venda);
	
	@Query("SELECT e FROM Venda e WHERE e.enderecoentrega LIKE %parteEndereco%")
	List<Venda> buscarParteEndereco(@Param("parteEndereco") String enderecoentrega);
	
	@Query("FROM Venda c WHERE c.enderecoentrega > :enderecoentrega")
	public List<Venda> buscarVendaEnderecoEntrega(String enderecoentrega);
}