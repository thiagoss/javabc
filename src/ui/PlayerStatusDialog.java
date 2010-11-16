package ui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Caixa de dialogo que mostra as estatisticas de um jogador
 * Extende JDialog
 * @author abmargb, thiagoss
 */
public class PlayerStatusDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6039959340427012777L;
	private JTextField plrWrongs = null;
	private JTextField plrRights = null;
	private JTextField textFieldPlayerName = null;
	private JTextField textFieldLogin = null;
	private JLabel labelPlayerName = null;
	private JLabel labelLogin = null;
	private JLabel labelRights = null;
	private JLabel labelWrongs = null;
	private JButton okButton = null;
	private Container container = null;
	
	/**
	 * Construtor de PlayerStatusDialog chamado por um frame
	 * @param f O frame que o chama
	 * @param title O titulo deste dialog
	 */
	protected PlayerStatusDialog(Frame f, String title) {
		super(f, title, true);
		configure();
		addComponents();
	}
	
	/**
	 * Construtor de PlayerStatusDialog chamado por um dialog
	 * @param f O dialog que o chama
	 * @param title O titulo deste dialog
	 */
	protected PlayerStatusDialog(JDialog f, String title) {
		super(f, title, true);
		configure();
		addComponents();
	}
	
	/**
	 * Inicializa as configuracoes basicas
	 *
	 */
	private void configure() {
		this.setSize(300, 220);
		container = getContentPane();
		container.setLayout(null);
	}
	
	/**
	 * Adiciona os componentes ao container deste dialog
	 *
	 */	
	private void addComponents() {
		container.add(getLabelPlayerName());
		container.add(getLabelLogin());
		container.add(getLabelRights());
		container.add(getLabelWrongs());
		container.add(getTextFieldPlayerName());
		container.add(getTextFieldLogin());
		container.add(getPlrRights());
		container.add(getPlrWrongs());
		container.add(getOkButton());
	}

	
	/**
	 * Retorna o label que representa o nome do jogador, caso eles esteja com valor nulo, inicializa-o
	 * @return o label que representa o nome do jogador.
	 */
	public JLabel getLabelPlayerName() {
		if (labelPlayerName == null) {
			labelPlayerName = new JLabel("Jogador: ");
			labelPlayerName.setBounds(10, 10, 100, 20);
					
		}
		return labelPlayerName;
	}
	
	/**
	 * Retorna o label que representa o login do jogador, caso eles esteja com valor nulo, inicializa-o
	 * @return o label que representa o login do jogador.
	 */
	public JLabel getLabelLogin() {
		if (labelLogin == null) {
			labelLogin = new JLabel("Login: ");
			labelLogin.setBounds(10, 40, 100, 20);
					
		}
		return labelLogin;
	}

	/**
	 * Retorna o label que representa os acertos do jogador, caso eles esteja com valor nulo, inicializa-o
	 * @return o label que representa os acertos do jogador.
	 */
	public JLabel getLabelRights() {
		if (labelRights == null) {
			labelRights = new JLabel("Acertos: ");
			labelRights.setBounds(10,70, 100, 20);
					
		}
		return labelRights;
	}

	/**
	 * Retorna o label que representa os erros do jogador, caso eles esteja com valor nulo, inicializa-o
	 * @return o label que representa os erros do jogador.
	 */
	public JLabel getLabelWrongs() {
		if (labelWrongs == null) {
			labelWrongs = new JLabel("Erros: ");
			labelWrongs.setBounds(10, 100, 100, 20);
					
		}
		return labelWrongs;
	}

	/**
	 * Retorna o botão de OK, caso eles esteja com valor nulo, inicializa-o
	 * @return o botão de OK.
	 */
	public JButton getOkButton() {
		if(okButton == null) {
			okButton = new JButton("Ok");
			okButton.setBounds(90, 145, 100, 30);
			okButton.addActionListener(this);
		}
		return okButton;
	}

	/**
	 * Retorna o field que contem o nome do jogador, caso eles esteja com valor nulo, inicializa-o
	 * @return o field que contem o nome do jogador.
	 */
	public JTextField getTextFieldPlayerName() {
		if (textFieldPlayerName == null) {
			textFieldPlayerName = new JTextField();
			textFieldPlayerName.setBounds(70, 10, 100, 20);
			textFieldPlayerName.setFocusable(false);
			textFieldPlayerName.setEditable(false);
			textFieldPlayerName.setBorder(null);
					
		}
		return textFieldPlayerName;
	}
	
	/**
	 * Seta um novo valor para o TextFieldPlayerName
	 * @param name O novo valor para o TextFieldPlayerName
	 */
	public void setTextFieldPlayerName(String name) {
		textFieldPlayerName.setText(name);
	}
	
	/**
	 * Retorna o field que contem o login do jogador, caso eles esteja com valor nulo, inicializa-o
	 * @return o field que contem o login do jogador.
	 */
	public JTextField getTextFieldLogin() {
		if (textFieldLogin == null) {
			textFieldLogin = new JTextField();
			textFieldLogin.setBounds(70, 40, 100, 20);
			textFieldLogin.setFocusable(false);
			textFieldLogin.setEditable(false);
			textFieldLogin.setBorder(null);
					
		}
		return textFieldLogin;
	}
	
	/**
	 * Seta um novo valor para o TextFieldLogin
	 * @param login O novo valor para o TextFieldLogin
	 */
	public void setTextFieldLogin(String login) {
		textFieldLogin.setText(login);
	}
	
	/**
	 * Retorna o field que contem os acertos do jogador, caso eles esteja com valor nulo, inicializa-o
	 * @return o field que contem os acertos do jogador.
	 */
	public JTextField getPlrRights() {
		if (plrRights == null) {
			plrRights = new JTextField();
			plrRights.setBounds(70, 70, 100, 20);
			plrRights.setFocusable(false);
			plrRights.setEditable(false);
			plrRights.setBorder(null);
					
		}
		return plrRights;
	}

	/**
	 * Seta um novo valor para o PlrRights
	 * @param rights O novo valor para o PlrRights
	 */
	public void setPlrRights(Long rights ) {
		plrRights.setText(Long.toString(rights));
	}
	
	/**
	 * Retorna o field que contem os erros do jogador, caso eles esteja com valor nulo, inicializa-o
	 * @return o field que contem os erros do jogador.
	 */
	public JTextField getPlrWrongs() {
		if (plrWrongs == null) {
			plrWrongs = new JTextField();
			plrWrongs.setBounds(70, 100, 100, 20);
			plrWrongs.setFocusable(false);
			plrWrongs.setEditable(false);
			plrWrongs.setBorder(null);
					
		}
		return plrWrongs;
	}

	/**
	 * Seta um novo valor para o PlrWrongs
	 * @param wrongs O novo valor para o PlrWrongs
	 */
	public void setPlrWrongs(Long wrongs ) {
		plrWrongs.setText(Long.toString(wrongs));
	}

	
	/**
	 * Configura as acoes determinadas pelos botoes
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == okButton) {
			this.dispose();
		}
		
	}
	

}
