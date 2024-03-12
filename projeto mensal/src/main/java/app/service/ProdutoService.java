package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Produto;
import app.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public String save(Produto produto) {
		this.produtoRepository.save(produto);
		return produto.getNome()+"Salvo com sucesso";
	}
	
	public String update(long id, Produto produto) {
		produto.setId(id);
		this.produtoRepository.save(produto);
		return "Produto não encontrado para alterar";
	}
	
	public List<Produto> listAll(){
		return this.produtoRepository.findAll();
	}
	
	public Produto findById(long idProduto) {
		Produto produto = this.produtoRepository.findById(idProduto).get();
		return produto;
	}
	
	public String delete(long idProduto) {
		this.produtoRepository.deleteById(idProduto);
		return "Não encontrado para alterar";
	}
	
	public List<Produto> findByNome(String nome){
		return this.produtoRepository.findByNome(nome);
	}
	
	public List<Produto> findByParteNome(String nome){
		return this.produtoRepository.findByParteNome(nome);
	}
	
	public List<Produto> findByValor(Double valor){
		return this.produtoRepository.findByValor(valor);
	}
	
	public List<Produto> buscarProdutoNome(String nome){
		return this.produtoRepository.buscarProdutoNome(nome);
	}
	
	public List<Produto> findByProduto(long id){
		Produto produto = new Produto();
		produto.setId(id);
		return this.produtoRepository.findByProduto(produto);
	}
}