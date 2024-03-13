package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Cliente;
import app.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	public String save(Cliente cliente) {
		this.clienteRepository.save(cliente);
		return cliente.getNome()+"Salvo com sucesso";
	}
	
	public String update(long id, Cliente cliente) {
		cliente.setId(id);
		this.clienteRepository.save(cliente);
		return "Cliente não encontrado para alterar";
	}
	
	public List<Cliente> listAll(){
		return this.clienteRepository.findAll();
	}
	
	public Cliente findById(long idCliente) {
		Cliente cliente = this.clienteRepository.findById(idCliente).get();
		return cliente;
	}
	
	public String delete(long idCliente) {
		this.clienteRepository.deleteById(idCliente);
		return "Não encontrado para deletar";
	}
	
	public List<Cliente> findByNome(String nome){
		return this.clienteRepository.findByNome(nome);
	}
	
	
	
	public List<Cliente> findByCpf(String cpf){
		return this.clienteRepository.findByCpf(cpf);
	}
	
	public List<Cliente> findByIdade(Integer idade){
		return this.clienteRepository.findByIdade(idade);
	}
	
	public List<Cliente> findByTelefone(String telefone){
		return this.clienteRepository.findByTelefone(telefone);
	}
	
	public List<Cliente> buscarClienteNome(String nome){
		return this.clienteRepository.buscarClienteNome(nome);
	}
	
}