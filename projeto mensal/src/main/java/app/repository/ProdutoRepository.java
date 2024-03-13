package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	public List<Produto> findByNome(String nome);
	
	public List<Produto> findByValor(Double valor);
	
	@Query("SELECT e FROM Produto e WHERE e.nome LIKE %:nome%")
	public List<Produto> buscarParteNome(String nome);
	
	@Query("FROM Produto c WHERE c.nome > :nome")
	public List<Produto> buscarProdutoNome(String nome);
}