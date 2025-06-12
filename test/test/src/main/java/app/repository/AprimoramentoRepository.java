package app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.AprimoramentoArma;

@Repository
public interface AprimoramentoRepository extends JpaRepository<AprimoramentoArma, Long>{
	//COnsulta por nome do aprimoramento (case insensitive, parcial);
	List<AprimoramentoArma> findByAprimoramentoPrincipalContainingIgnoreCase(String nome);
	
	//FindById já está disponível via JpaRepository, mas pode-se explitar se preferir;
	Optional<AprimoramentoArma> findById(Long id);
}
/*

*/