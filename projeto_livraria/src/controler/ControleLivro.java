package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import DAI.LivroDAI;
import View.LivroOrganizar;
import modelo.LivroModel;

public class ControleLivro {//begin
	private LivroOrganizar view = new LivroOrganizar();
	
	public ControleLivro() {//begin1
		MouseListener ouvinte2 = new MouseListener() {//begin2
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			
			public void mouseClicked(MouseEvent e) {//begin3
				if(e.getSource() == view.getTable()) {
					int linha = view.getTable().getSelectedRow();
					int id = (int) view.getTable().getValueAt(linha, 0);
					String titulo = (String) view.getTable().getValueAt(linha, 1);
					String autor = (String) view.getTable().getValueAt(linha, 2);
					String ano = (String) view.getTable().getValueAt(linha, 3);
					String local = (String) view.getTable().getValueAt(linha, 4);
					String editora = (String) view.getTable().getValueAt(linha, 5);
					
					view.getCampoIdAlterar().setText(String.format("%d", id));
					view.getCampoTituloAlterar().setText(titulo);
					view.getCampoAutorAlterar().setText(autor);
					view.getCampoAnoAlterar().setText(ano);
					view.getCampoLocalAlterar().setText(local);
					view.getCampoEditoraAlterar().setText(editora);
					view.getBotaoAlterar().setEnabled(true);
					view.getBotaoExcluir().setEnabled(true);
				}
				
			}//end3
			
		};//end2
		
		ActionListener ouvinte = new ActionListener() {//begin4
			@Override
			public void actionPerformed(ActionEvent e) {//begin5
				if(e.getSource() == view.getBotaoSalvar()) {
					LivroModel livro = new LivroModel();
					livro.setTitulo(view.getCampoTitulo().getText());
					livro.setAutor(view.getCampoAutor().getText());
					livro.setAno(view.getCampoAno().getText());
					livro.setLocal(view.getCampoLocal().getText());
					livro.setEditora(view.getCampoEditora().getText());
					
					LivroDAI dai = new LivroDAI();
					dai.salvar(livro);
					
					JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
					List<LivroModel> listaLivros = dai.listar();
					view.carregarTable(listaLivros, ouvinte2);
					view.limparCampos();
				} else
					if(e.getSource() == view.getBotaoAlterar()) {
						LivroModel livro = new LivroModel();
						String id = view.getCampoIdAlterar().getText();
						if(!id.equals("")) {
							livro.setId(Integer.parseInt(id));
							livro.setTitulo(view.getCampoTituloAlterar().getText());
							livro.setAutor(view.getCampoAutorAlterar().getText());
							livro.setAno(view.getCampoAnoAlterar().getText());
							livro.setLocal(view.getCampoLocalAlterar().getText());
							livro.setEditora(view.getCampoEditoraAlterar().getText());
							
							LivroDAI dai = new LivroDAI();
							dai.alterar(livro);
							
							JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
							List<LivroModel> listaLivros = dai.listar();
							view.carregarTable(listaLivros,ouvinte2);
							view.limparCampos();
						}
					} else
						if(e.getSource() == view.getBotaoExcluir()) {
							String id = view.getCampoIdAlterar().getText();
							if(!id.equals("")) {
								LivroDAI dai = new LivroDAI();
								dai.deletar(Integer.parseInt(id));
								JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
								List<LivroModel> listaLivros = dai.listar();
								view.carregarTable(listaLivros,ouvinte2);
								view.limparCampos();
							}
						}
			}//end5
			
		};//end4
		
		view.configurarComponentes(ouvinte);
		
		LivroDAI dai = new LivroDAI();
		List<LivroModel> listaLivros = dai.listar();
		view.carregarTable(listaLivros,ouvinte2);
	}//end1
	
}//end