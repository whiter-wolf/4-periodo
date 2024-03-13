package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Funcionario;
import app.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public String save(Funcionario funcionario) {
		this.funcionarioRepository.save(funcionario);
		return funcionario.getNome()+"Salvo com sucesso";
	}
	
	public String update(long id, Funcionario funcionario) {
		funcionario.setId(id);
		this.funcionarioRepository.save(funcionario);
		return "Funcionario não encontrado para alterar";
	}
	
	public List<Funcionario> listAll(){
		return this.funcionarioRepository.findAll();
	}
	
	public Funcionario findById(long idFuncionario) {
		Funcionario funcionario = this.funcionarioRepository.findById(idFuncionario).get();
		return funcionario;
	}
	
	public String delete(long idFuncionario) {
		this.funcionarioRepository.deleteById(idFuncionario);
		return "Não encontrado para deletar";
	}
	
	public List<Funcionario> findByNome(String nome){
		return this.funcionarioRepository.findByNome(nome);
	}
	
	
	
	public List<Funcionario> findByIdade(Integer idade){
		return this.funcionarioRepository.findByIdade(idade);
	}
	
	public List<Funcionario> findByMatricula(String matricula){
		return this.funcionarioRepository.findByMatricula(matricula);
	}
	
	public List<Funcionario> buscarFuncionarioNome(String nome){
		return this.funcionarioRepository.buscarFuncionarioNome(nome);
	}
	
}