package ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Classe que extende o JDialog <br>
 * Eh um Frame que possui uma area de texto (com barra de rolagem) para exibicao de informacoes.
 * Contem tambem um botao para voltar e um titulo.
 * @author thiagoss, abmargb
 *
 */
public class HelpDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7557860239468685376L;
	
	
	private JLabel title = null;
	private JTextArea textAreaHelpInfo = null;
	private JScrollPane scrollAreaHelpInfo = null;
	private JButton backButton = null;
	private Container container = null;
	private Font font = new Font("monospaced", Font.BOLD, 20);
	private Font textFont = new Font("monospaced", Font.PLAIN, 14);
	
	/**
	 * Cria um HelpDialog
	 * @param parent o Frame pai deste HelpDialog
	 * @param frameTitle o titulo a ser exibido na barra de titulo do HelpDialog
	 * @param title o titulo a ser exibido dentro do HelpDialog
	 * @param text o texto a ser exibido pelo HelpDialog
	 * @param buttonName o nome do botao que faz fechar o HelpDialog
	 */
	public HelpDialog(Frame parent, String frameTitle, String title, String text, String buttonName) {
		super(parent, frameTitle, true);
		configure();
		initTextArea(text);
		initScrollPane();
		initButton(buttonName);
		initLabel(title);
		
	}
	
	/**
	 * Inicializa as configuracoes basicas do objeto
	 *
	 */
	private void configure() {
		container = getContentPane();
		container.setLayout(null);
		this.setSize(500, 600);
		this.setResizable(false);
		
	}
	
	/**
	 * Inicializa o JTextArea deste objeto
	 * @param text o texto a ser exibido no JTextArea
	 */
	private void initTextArea(String text) {
		textAreaHelpInfo = new JTextArea();
		textAreaHelpInfo.setLineWrap(true);
		textAreaHelpInfo.setWrapStyleWord(true);
		textAreaHelpInfo.setSize(400, 450);
		textAreaHelpInfo.setText(text);
		textAreaHelpInfo.setEditable(false);
		textAreaHelpInfo.setFont(textFont);
	}
	
	/**
	 * Inicializa o JScrollPane deste objeto
	 *
	 */
	private void initScrollPane() {
		scrollAreaHelpInfo = new JScrollPane(textAreaHelpInfo);
		scrollAreaHelpInfo.setBounds(40, 50, 400, 450);
		container.add(scrollAreaHelpInfo);
	}
	
	/**
	 * inicializa o JButton deste objeto
	 * @param s o texto a ser exibido no botao
	 */
	private void initButton(String s) {
		backButton = new JButton(s);
		backButton.setBounds(40, 520, 100, 30);
		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				closeFrame();
				
			}
			
		});
		container.add(backButton);
	}
	
	/**
	 * Inicializa o JLabel deste objeto
	 * @param t o texto a ser exibido no JLabel
	 */
	private void initLabel(String t) {
		title = new JLabel(t);
		title.setBounds(40, 20, 200, 30);
		title.setFont(font);
		container.add(title);
	}
	
	/**
	 * Fecha este HelpDialog
	 *
	 */
	public void closeFrame() {
		this.dispose();
	}
	                        
	
}
