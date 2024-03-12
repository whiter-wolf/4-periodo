package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	public List<Produto> findByNome(String nome);
	
	public List<Produto> findByParteNome(String nome);
	
	public List<Produto> findByValor(Double valor);
	
	public List<Produto> findByProduto(Produto produto);
	
	@Query("SELECT e FROM Produto e WHERE e.nome LIKE %parteNome%")
	List<Produto> buscarParteNome(@Param("parteNome") String parteNome);
	
	@Query("FROM Produto c WHERE c.nome > :nome")
	public List<Produto> buscarProdutoNome(String nome);
}