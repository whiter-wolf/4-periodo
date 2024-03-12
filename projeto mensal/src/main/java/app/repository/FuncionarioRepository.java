package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	public List<Funcionario> findByNome(String nome);
	
	public List<Funcionario> findByParteNome(String nome);
	
	public List<Funcionario> findByIdade(Integer idade);
	
	public List<Funcionario> findByMatricula(String matricula);
	
	public List<Funcionario> findByFuncionario(Funcionario funcionario);
	
	@Query("SELECT e FROM Funcionario e WHERE e.nome LIKE %:parteNome%")
	List<Funcionario> buscarParteNome(@Param("parteNome") String parteNome);
	
	@Query("FROM Funcionario c WHERE c.nome > :nome")
	public List<Funcionario> buscarFuncionarioNome(String nome);
}