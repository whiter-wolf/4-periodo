package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import modelo.LivroModel;

public class LivroOrganizar extends JFrame{
	private JTable table;
	private JScrollPane painel;
	private JPanel painelSalvar = new JPanel();
	private JPanel painelAlterarExcluir = new JPanel();
	private JLabel textoTitulo = new JLabel("Título:");
	private JTextField campoTitulo = new JTextField(20);
	private JLabel textoAutor = new JLabel("Autor");
	private JTextField campoAutor = new JTextField(20);
	private JLabel textoAno = new JLabel("Ano");
	private JTextField campoAno = new JTextField(20);
	private JLabel textoLocal = new JLabel("Local");
	private JTextField campoLocal = new JTextField(20); 
	private JLabel textoEditora = new JLabel("Editora");
	private JTextField campoEditora = new JTextField(20);
	private JButton botaoSalvar = new JButton("Salvar");
	
	private JLabel textoIdAlterar = new JLabel("Id do BD:");
	private JTextField campoIdAlterar = new JTextField(20);
	private JLabel textoTituloAlterar = new JLabel("Título");
	private JTextField campoTituloAlterar = new JTextField(20);
	private JLabel textoAutorAlterar = new JLabel("Autor");
	private JTextField campoAutorAlterar = new JTextField(20);
	private JLabel textoAnoAlterar = new JLabel("Ano");
	private JTextField campoAnoAlterar = new JTextField(20);
	private JLabel textoLocalAlterar = new JLabel("Local");
	private JTextField campoLocalALterar = new JTextField(20);
	private JLabel textoEditoraAlterar = new JLabel("Editora");
	private JTextField campoEditoraAlterar = new JTextField(20);
	private JButton botaoAlterar = new JButton("Alterar");
	private JButton botaoExcluir = new JButton("Excluir");
	
	public LivroOrganizar() {
		this.setSize(600, 500);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void configurarComponentes(ActionListener ouvinte) {
		painelSalvar.add(textoTitulo);
		painelSalvar.add(campoTitulo);
		painelSalvar.add(textoAutor);
		painelSalvar.add(campoAutor);
		painelSalvar.add(textoAno);
		painelSalvar.add(campoAno);
		painelSalvar.add(textoLocal);
		painelSalvar.add(campoLocal);
		painelSalvar.add(textoEditora);
		painelSalvar.add(campoEditora);
		botaoSalvar.addActionListener(ouvinte);
		painelSalvar.add(botaoSalvar);
		painelSalvar.setPreferredSize(new Dimension(250, 300));
		this.add(painelSalvar);
		
		painelAlterarExcluir.add(textoIdAlterar);
		painelAlterarExcluir.add(campoIdAlterar);
		painelAlterarExcluir.add(textoTituloAlterar);
		painelAlterarExcluir.add(campoTituloAlterar);
		painelAlterarExcluir.add(textoAutorAlterar);
		painelAlterarExcluir.add(campoAutorAlterar);
		painelAlterarExcluir.add(textoAnoAlterar);
		painelAlterarExcluir.add(campoAnoAlterar);
		painelAlterarExcluir.add(textoLocalAlterar);
		painelAlterarExcluir.add(campoLocalALterar);
		painelAlterarExcluir.add(textoEditoraAlterar);
		painelAlterarExcluir.add(campoEditoraAlterar);
		botaoAlterar.addActionListener(ouvinte);
		botaoExcluir.addActionListener(ouvinte);
		painelAlterarExcluir.add(botaoAlterar);
		painelAlterarExcluir.add(botaoExcluir);
		painelAlterarExcluir.setPreferredSize(new Dimension(250, 330));
		this.add(painelAlterarExcluir);
		this.limparCampos();
	}
	
	public void carregarTable(List<LivroModel> listaLivros, MouseListener ouvinte2) {
		if(painel != null) 
			this.remove(painel);
			String[] nomesColunas = new String[] {
				"ID", "Título", "Autor", "Ano", "Local", "Editora"	
			};
			
			Object[][] dados = new Object[listaLivros.size()][6];
			for(int i = 0; i<listaLivros.size(); i++) {
				dados[i][0] = listaLivros.get(i).getId();
				dados[i][1] = listaLivros.get(i).getTitulo();
				dados[i][2] = listaLivros.get(i).getAutor();
				dados[i][3] = listaLivros.get(i).getAno();
				dados[i][4] = listaLivros.get(i).getLocal();
				dados[i][5] = listaLivros.get(i).getEditora();
			}
			table = new JTable(dados,nomesColunas);
			painel = new JScrollPane(table);
			painel.setPreferredSize(new Dimension(520, 115));
			this.table.addMouseListener(ouvinte2);
			this.add(painel);
			this.revalidate();
	}
	
	public void limparCampos() {
		this.campoIdAlterar.setText("");
		this.campoTituloAlterar.setText("");
		this.campoAutorAlterar.setText("");
		this.campoAnoAlterar.setText("");
		this.campoLocalALterar.setText("");
		this.campoEditoraAlterar.setText("");
		this.campoTitulo.setText("");
		this.campoAutorAlterar.setText("");
		this.campoAno.setText("");
		this.campoLocal.setText("");
		this.campoEditora.setText("");
		this.botaoAlterar.setEnabled(false);
		this.botaoExcluir.setEnabled(false);
	}
	
	public JTextField getCampoTitulo() {
		return campoTitulo;
	}
	public void setCampoTitulo(JTextField campoTitulo) {
		this.campoTitulo = campoTitulo;
	}
	public JTextField getCampoAutor() {
		return campoAutor;
	}
	public void setCampoAutor(JTextField campoAutor) {
		this.campoAutor = campoAutor;
	}
	public JTextField getCampoAno() {
		return campoAno;
	}
	public void setCampoAno(JTextField campoAno) {
		this.campoAno = campoAno;
	}
	public JTextField getCampoLocal() {
		return campoLocal;
	}
	public void setCampoLocal(JTextField campoLocal) {
		this.campoLocal = campoLocal;
	}
	public JTextField getCampoEditora() {
		return campoEditora;
	}
	public void setCampoEditora(JTextField campoEditora) {
		this.campoEditora = campoEditora;
	}
	public JButton getBotaoSalvar() {
		return botaoSalvar;
	}
	public void setBotaoSalvar(JButton botaoSalvar) {
		this.botaoSalvar = botaoSalvar;
	}
	public JButton getBotaoAlterar() {
		return botaoAlterar;
	}
	public void setBotaoAlterar(JButton botaoAlterar) {
		this.botaoAlterar = botaoAlterar;
	}
	public JButton getBotaoExcluir() {
		return botaoExcluir;
	}
	public void setBotaoExcluir(JButton botaoExcluir) {
		this.botaoExcluir = botaoExcluir;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public JTextField getCampoIdAlterar() {
		return campoIdAlterar;
	}
	public void setCampoIdAlterar(JTextField campoIdAlterar) {
		this.campoIdAlterar = campoIdAlterar;
	}
	public JTextField getCampoTituloAlterar() {
		return campoTituloAlterar;
	}
	public void setCampoTituloAlterar(JTextField campoTituloAlterar) {
		this.campoTituloAlterar = campoTituloAlterar;
	}
	public JTextField getCampoAutorAlterar() {
		return campoAutorAlterar;
	}
	public void setCampoAutorAlterar(JTextField campoAutorAlterar) {
		this.campoAutorAlterar = campoAutorAlterar;
	}
	public JTextField getCampoAnoAlterar() {
		return campoAnoAlterar;
	}
	public void setCampoAnoAlterar(JTextField campoAnoAlterar) {
		this.campoAnoAlterar = campoAnoAlterar;
	}
	public JTextField getCampoLocalAlterar() {
		return campoLocalALterar;
	}
	public void setCampoLocalAlterar(JTextField campoLocalAlterar) {
		this.campoLocalALterar = campoLocalAlterar;
	}
	
	public void setCampoEditoraAlterar(JTextField campoEditoraAlterar) {
		this.campoEditoraAlterar = campoEditoraAlterar;
	}
	public JTextField getCampoEditoraAlterar() {
		return campoEditoraAlterar;
	}
	
	
}