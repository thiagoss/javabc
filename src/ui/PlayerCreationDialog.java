package ui;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import player.Player;

/**
 * Classe que extende JDialog<br> 
 * Representa a caixa de dialogo de criaçao de palavra
 * @author  thiagoss abmargb
 */
public class PlayerCreationDialog extends JDialog {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5808704544403058688L;
	private JButton okButton = null;
	private JButton cancelButton = null;
	private JLabel nameLabel = null;
	private JLabel loginLabel = null;
	private JTextField nameField = null;
	private JTextField loginField = null;
	private JLabel titleLabel = null;
	
	private Container container = null;
	
	private Player player = null;
	
	/**
	 * Construtor desta caixa, a partir de um Dialog
	 * @param f O dialog que chama esta caixa
	 * @param p O player a ser modificado
	 */
	public PlayerCreationDialog(Dialog f, Player p) {
		super(f, "Criando Jogador", true);
		player = p;
		configure();
		addComponents();
		this.setVisible(true);
	}
	
	/**
	 * Construtor desta caixa, a partir de um Frame
	 * @param f O frame que chama esta caixa
	 */
	public PlayerCreationDialog(Frame f) {
		super(f, "Criando Jogador", true);
		configure();
		addComponents();
		this.setVisible(true);

	}
	
	/**
	 * Retorna o player criado
	 * @return  Returna o player criado.
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Adiciona os componentes ao container
	 *
	 */
	public void addComponents() {
		container.add(this.getCancelButton());
		container.add(this.getOkButton());
		container.add(this.getNameLabel());
		container.add(this.getNameField());
		container.add(this.getLoginLabel());
		container.add(this.getLoginField());
		container.add(this.getTitleLabel());
	}
	
	/**
	 * Configura as caracteristicas gerais deste dialog
	 *
	 */
	public void configure() {
		
		this.setSize(500, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		container = getContentPane();
		container.setLayout(null);
		
	}
	
	/**
	 * Retorna o okButton <br> Caso eles esteja com valor nulo, inicializa-o
	 * @return  Retorna o okButton.
	 */
	public JButton getOkButton() {
		if(okButton == null) {
			okButton = new JButton("OK");
			okButton.setBounds(350, 50, 100, 30);
			okButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					ok();
					
				}
				
			});
		}
		return okButton;
	}
	
	/**
	 * Retorna o cancelButton. <br> Caso eles esteja com valor nulo, inicializa-o
	 * @return  Retorna o cancelButton.
	 */
	public JButton getCancelButton() {
		if(cancelButton == null) {
			cancelButton = new JButton("Cancelar");
			cancelButton.setBounds(350, 90, 100, 30);
			cancelButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					cancel();
					
					
				}
				
			});
		}
		return cancelButton;
	}
	
	/**
	 * Retorna o Label que contém o titulo. <br> Caso eles esteja com valor nulo, inicializa-o
	 * @return  Retorna o Label que contém o titulo.
	 */
	public JLabel getTitleLabel() {
		if(titleLabel == null) {
			titleLabel = new JLabel("Preencha os campos abaixo");
			titleLabel.setBounds(20, 20, 200, 30);
		}
		return titleLabel;
	}
	
	/**
	 * Retorna o Label que contém o login. <br> Caso eles esteja com valor nulo, inicializa-o
	 * @return  Retorna o Label que contém o login.
	 */
	public JLabel getLoginLabel() {
		if(loginLabel == null) {
			loginLabel = new JLabel("Login: ");
			loginLabel.setBounds(20, 50, 100, 30);
		}
		return(loginLabel);
	}
	
	/**
	 * Retorna o Label que contém o nome. <br> Caso eles esteja com valor nulo, inicializa-o
	 * @return  Retorna o Label que contém o nome.
	 */
	public JLabel getNameLabel() {
		if(nameLabel == null) {
			nameLabel = new JLabel("Nome: ");
			nameLabel.setBounds(20, 90, 100, 30);
		}
		return(nameLabel);
	}
	
	/**
	 * Retorna o field referente ao nome. <br> Caso eles esteja com valor nulo, inicializa-o
	 * @return  Retorna o field referente ao nome.
	 */
	public JTextField getNameField() {
		if(nameField == null) {
			nameField = new JTextField();
			nameField.setBounds(125, 90, 200, 30);
			nameField.setEditable(true);
		}
		return nameField;
	}
	
	/**
	 * Retorna o field referente ao login. <br> Caso eles esteja com valor nulo, inicializa-o
	 * @return  Retorna o field referente ao login.
	 */
	public JTextField getLoginField() {
		if(loginField == null) {
			loginField = new JTextField();
			loginField.setBounds(125, 50, 200, 30);
			loginField.setEditable(true);
		}
		return loginField;
	}
	
	/**
	 * Cria uma Janela de criação de player a partir de um dialog 
	 * e retorna o player criado
	 * @param dialog A caixa de dialogo que chama esta caixa
	 * @return Retorna o player criado
	 */
	public static Player createPlayerDialog(Dialog dialog) {
		Player p = null;
		PlayerCreationDialog pcd = new PlayerCreationDialog(dialog, p);
		
		return pcd.getPlayer();
		
	}
	
	/**
	 * Cria uma Janela de criação de player a partir de um frame 
	 * e retorna o player criado
	 * @param frame O frama que chama esta caixa
	 * @return Retorna o player criado
	 */
	public static Player createPlayerDialog(Frame frame) {

		PlayerCreationDialog pcd = new PlayerCreationDialog(frame);

		return pcd.getPlayer();
		
	}
	
	/**
	 * Confirma os dados do jogador a ser cadastrado e fecha o dialog
	 *
	 */
	private void ok() {
		String name = nameField.getText();
		String login = loginField.getText();
		if(!name.equals("") && !login.equals("") && name != null && login != null) {
			player = new Player(name, login);
			this.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(this, "Preencha os campos corretamente",
					"Atencao", JOptionPane.WARNING_MESSAGE);
				
			
		}
	}
	
	/**
	 * Cancela a criação do player e fecha o dialog
	 *
	 */
	private void cancel() {
		player = null;
		this.dispose();
	}
	
	
}
