package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Venda;
import app.repository.VendaRepository;

@Service
public class VendaService {
	@Autowired
	private VendaRepository vendaRepository;
	
	public String save(Venda venda) {
		double soma = 0;
		if(venda.getProdutos() != null)
		for(int i=0; i<venda.getProdutos().size(); i++) {
			soma += venda.getProdutos().get(i).getValor();
		}
		venda.setValortotal(soma);
		
		this.vendaRepository.save(venda);
		return venda.getEnderecoentrega()+"Salvo com sucesso";
	}
	
	public String update(long id, Venda venda) {
		venda.setId(id);
		this.vendaRepository.save(venda);
		return "Venda não encontrada para alterar";
	}
	
	public List<Venda> listAll(){
		return this.vendaRepository.findAll();
	}
	
	public Venda findById(long idVenda) {
		Venda venda = this.vendaRepository.findById(idVenda).get();
		return venda;
	}
	
	public String delete(long idVenda) {
		this.vendaRepository.deleteById(idVenda);
		return "Não encontrado para deletar";
	}
	
	public List<Venda> findByEnderecoEntrega(String enderecoentrega){
		return this.vendaRepository.findByEnderecoEntrega(enderecoentrega);
	}
	
	public List<Venda> findByParteEndereco(String enderecoentrega){
		return this.vendaRepository.findByParteEndereco(enderecoentrega);
	}
	
	public List<Venda> findByValorTotal(Double valortotal){
		return this.vendaRepository.findByValorTotal(valortotal);
	}
	
	public List<Venda> buscarVendaEnderecoEntrega(String enderecoentrega){
		return this.vendaRepository.buscarVendaEnderecoEntrega(enderecoentrega);
	}
	
	public List<Venda> findByVenda(long id){
		Venda venda = new Venda();
		venda.setId(id);
		return this.vendaRepository.findByVenda(venda);
	}
}