package ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Classe que extende o JPanel, util para exibicao de atributos de jogos. <br> Os 3 atributos que podem ser exibidos sao: Nome, acertos e erros. Nome exibe um String, acertos e erros exibem um long. <br><br> A classe exibe os atributos na forma: "nome do atributo": valor do atributo
 * @author  thiagoss abmargb
 */
public class PlayerStatusPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField plrWrongs = null;
	private JTextField plrRights = null;
	private JTextField textFieldPlayerName = null;
	private JLabel labelPlayerName = null;
	private JLabel labelRights = null;
	private JLabel labelWrongs = null;
	private Font titleFont = null;
	private Font textFont = null;
	private Color textColor = null;
	private Color titleColor = null;
			
	/**
	 * Configura as caracteristicas gerais desta classe
	 *
	 */
	private void configure() {
		setFocusable(false);
		this.setLayout(null);
	}
	
	/**
	 * Inicializa os JTextFields desta classe
	 *
	 */
	private void initTextFields(Font f, Color c) {
		plrWrongs = new JTextField();
		
		plrWrongs.setBounds(125, 63, 100, 20);
		plrWrongs.setBorder(null);
		plrWrongs.setEditable(false);
		plrWrongs.setFocusable(false);
		add(plrWrongs);
		
		plrRights = new JTextField();
		plrRights.setBounds(125,33,100,20);
		plrRights.setBorder(null);
		plrRights.setEditable(false);
		plrRights.setFocusable(false);
		add(plrRights);
		
		textFieldPlayerName = new JTextField();
		textFieldPlayerName.setBounds(70, 0, 150, 25);
		textFieldPlayerName.setBorder(null);
		textFieldPlayerName.setEditable(false);
		textFieldPlayerName.setFocusable(false);
		add(textFieldPlayerName);
		
		setTextFont(f);
		setTextFontColor(c);
	}
	
	/**
	 * Inicializa os JLabels desta Classe
	 *
	 */
	private void initLabels(Font f, Color c) {
		labelPlayerName = new JLabel("Nome: ");
		labelPlayerName.setBounds(0, 0, 150, 25);
		labelPlayerName.setFocusable(false);
		add(labelPlayerName);
		
		labelRights = new JLabel("Acertos: ");
		labelRights.setBounds(25, 30, 150, 25);
		labelRights.setFocusable(false);
		add(labelRights);
		
		labelWrongs = new JLabel("Erros: ");
		labelWrongs.setBounds(25, 60, 100, 25);
		labelWrongs.setFocusable(false);
		add(labelWrongs);
		
		setTitleFont(f);
		setTitleFontColor(c);
		
	}
	
	/**
	 * Modifica o valor de acertos exibido
	 * @param rights o novo numero de acertos
	 */
	public void setRights (long rights) {
		plrRights.setText(Long.toString(rights));
	}
	
	/**
	 * Modifica o valor de erros exibido
	 * @param wrongs o novo numero de erros
	 */
	public void setWrongs (long wrongs) {
		plrWrongs.setText(Long.toString(wrongs));
	}
	
	/**
	 * Constroi um novo PlayerStatusPanel usando as Fontes e a Cor passada nos parametros
	 * @param title a Font usada para os nomes dos atributos exibidos (Nome, Acertos, Erros)
	 * @param text a Font usada para exibicao dos valores dos atributos
	 * @param textColor a cor do texto exibido
	 * @param titleColor a cor do fonte do titulo
	 */
	public PlayerStatusPanel(Font title, Font text, Color titleColor, Color textColor) {
		
		
		configure();
		setVisible(false);
		initTextFields(text, textColor);
		initLabels(title, titleColor);
		
	}
	
	/**
	 * Modifica a Font dos titulos (nomes dos atributos)
	 * @param f  a nova Font
	 */
	public void setTitleFont(Font f) {
		titleFont = f;
		labelPlayerName.setFont(titleFont);
		labelWrongs.setFont(titleFont);
		labelRights.setFont(titleFont);
	}
	
	/**
	 * Modifica a Font dos atributos exibidos (o valor dos atributos)
	 * @param f  a nova Font
	 */
	public void setTextFont(Font f) {
		textFont = f;
		plrWrongs.setFont(textFont);
		plrRights.setFont(textFont);
		textFieldPlayerName.setFont(textFont);
	}
	
	/**
	 * Modifica a cor da fonte dos atributos exibidos (o valor dos atributos)
	 * @param c a nova cor
	 */
	public void setTextFontColor(Color c) {
		textColor = c;
		plrWrongs.setForeground(textColor);
		textFieldPlayerName.setForeground(textColor);
		plrRights.setForeground(textColor);
	}
	
	/**
	 * Modifica a cor da fonte dos nomes dos atributos exibidos
	 * @param c a nova cor
	 */
	public void setTitleFontColor(Color c) {
		titleColor = c;
		labelPlayerName.setForeground(c);
		labelRights.setForeground(c);
		labelWrongs.setForeground(c);
	}
	
	/**
	 * Retorna a Font dos nomes dos atributos
	 * @return  um objeto Font
	 */
	public Font getTitleFont() {
		return titleFont;
	}
	/**
	 * Retorna a Font usada para os valores dos atributos
	 * @return  um objeto Font
	 */
	public Font getTextFont() {
		return textFont;
	}
	
	/**
	 * Retorna a cor da fonte usada nos valores dos atributos
	 * @return objeto Color
	 */
	public Color getTextFontColor() {
		return textColor;
	}
	
	/**
	 * Retorna a cor da fonte usada nos nomes dos atributos
	 * @return um objeto Color
	 */
	public Color getTitleFontColor() {
		return titleColor;
	}
	
	/**
	 * Modifica o nome do jogador exibido
	 * @param name String com o nome do jogador
	 */
	public void setPlayerName(String name) {
		textFieldPlayerName.setText(name);
	}

}
