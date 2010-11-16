package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import util.FileHandling;

/**
 * Classe que extende o JDialog<br> Representa a caixa de dialogo "Sobre", que contem informacoes sobre o programa
 * @author  thiagoss abmargb
 */
public class AboutDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5053166577540541332L;
	private final static String LINESEPARATOR = System.getProperty("line.separator");
	private final static String SEPARADOR = FileHandling.SEPARADOR;
	private JLabel imageLabel = null;
	private JTextArea aboutArea = null;
	private JButton okButton = null;
	
	private Container container = null;
	
	/**
	 * Constroi um novo AboutDialog<br> 
	 * Recebe como parametros o frame que o chamou e o titulo do dialog
	 * @param f O frame que chama este AboutDialog 
	 * @param title O titulo da dialog
	 */
	protected AboutDialog(Frame f, String title) {
		super(f, title, true);
		configure();
		addComponents();
	}
	
	/**
	 * Configura as caracteristicas gerais deste dialog
	 *
	 */
	private void configure() {
		this.setSize(300, 230);
		container = getContentPane();
		container.setLayout(null);
	}
	
	/**
	 * Adiciona os componentes ao container
	 *
	 */
	private void addComponents() {
		container.add(getImageLabel());
		container.add(getOkButton());
		container.add(getAboutArea());
	}
	
	/**
	 * Retorna o botao de OK
	 * @return  O botao de OK
	 */
	public JButton getOkButton() {
		if(okButton == null) {
			okButton = new JButton("Ok");
			okButton.setBounds(100, 150, 100, 30);
			okButton.addActionListener(this);
		}
		return okButton;
	}
	
	/**
	 * Retorna o textArea
	 * @return  O textArea
	 */
	public JTextArea getAboutArea() {
		if(aboutArea == null) {
			aboutArea = new JTextArea("JavABC" +LINESEPARATOR+ 
									  "Versão 1.0 Beta" +LINESEPARATOR+
									  "Copyright (C) 2006" +LINESEPARATOR+
									  "JavABC Team");
			aboutArea.setBounds(150,10,150,100);
			aboutArea.setBackground(null);
			aboutArea.setFont(new Font("", Font.PLAIN, 16));
			aboutArea.setEditable(false);
			aboutArea.setFocusable(false);
		}
		return aboutArea;
	}
	
	/**
	 * Retorna o label da imagem
	 * @return  O label da imagem
	 */
	public JLabel getImageLabel() {
		if (imageLabel == null) {
			try {
				imageLabel = new JLabel(new ImageIcon(ImageIO.read(new File("data"+SEPARADOR+
						"config"+SEPARADOR+"javabc_logo.gif"))));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Um logo nao pode ser carregado", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
			imageLabel.setBounds(10, 10, 128, 128);
			imageLabel.setBackground(Color.WHITE);
		}
		return imageLabel;
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
