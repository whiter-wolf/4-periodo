package DAI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.LivroModel;

public class LivroDAI {//begin
	private Connection conexao;
	private PreparedStatement statement;
	
	public void salvar(LivroModel livro) {//begin1
		try {
			BDConexao BDconexao = new BDConexao();
			conexao = BDconexao.getCon();
			conexao.setAutoCommit(false);
			
			String sql = "INSERT INTO livro values (0,?,?,?)";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, livro.getTitulo());
			statement.setString(2, livro.getAutor());
			statement.setString(3, livro.getAno());
			statement.setString(4, livro.getLocal());
			statement.setString(5, livro.getEditora());
			statement.executeUpdate();
		}
		catch(Exception ex) {
			try {
				conexao.rollback();
			} catch(SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally {
			try {
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}//end1
	
	public void alterar(LivroModel livro) {//begin2
		try {
			BDConexao BDconexao = new BDConexao();
			conexao = BDconexao.getCon();
			conexao.setAutoCommit(false);
			String sql = "UPDATE livro SET titulo = ?, autor = ?, ano = ?, "
					+ "local = ?, editora = ? WHERE id = ?";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, livro.getTitulo());
			statement.setString(2, livro.getAutor());
			statement.setString(3, livro.getAno());
			statement.setString(4, livro.getLocal());
			statement.setString(5, livro.getEditora());
			statement.setInt(6, livro.getId());
			statement.executeUpdate();
		}
		catch(Exception ex) {
			try {
				conexao.rollback();
			} catch(SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally {
			try {
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}//end2
	
	public void deletar(int id) {//begin3
		try {
			BDConexao BDconexao = new BDConexao();
			conexao = BDconexao.getCon();
			conexao.setAutoCommit(false);
			String sql = "DELETE FROM livro WHERE id = ?";
			statement = conexao.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		}
		catch(Exception ex) {
			try {
				conexao.rollback();
			} catch(SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally {
			try {
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}//end3
	
	public List<LivroModel> listar(){//begin4
		try {
			BDConexao BDconexao = new BDConexao();
			conexao = BDconexao.getCon();
			conexao.setAutoCommit(false);
			String sql = "SELECT id,titulo,autor,ano,local,editora FROM livro";
			statement = conexao.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			List<LivroModel> listaLivros = new ArrayList<>();
			while(result.next()) {
				LivroModel livro = new LivroModel();
				livro.setId(result.getInt(1));
				livro.setTitulo(result.getString(2));
				livro.setAutor(result.getString(3));
				livro.setAno(result.getString(4));
				livro.setLocal(result.getString(5));
				livro.setEditora(result.getString(6));
				listaLivros.add(livro);
			}
			return listaLivros;
		}
		catch(Exception ex) {
			try {
				conexao.rollback();
			} catch(SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
			return null;
		}
		finally {
			try {
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}//end4
	
}//end