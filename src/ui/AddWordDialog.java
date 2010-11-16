package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import util.ImageFilter;
import word.WordWithImage;

/**
 * Classe que extende JDialog<br> 
 * Representa a caixa de dialogo "Adiciona Palavra"
 * É chamada a partir de outra caixa de dialogo, a WordManager
 * @author  thiagoss abmargb
 */
public class AddWordDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7753063534532773726L;
	private JButton cancelButton = null;
	private JButton okButton = null;
	private JButton selectButton = null;
	private JLabel imageLabel = null;
	private JLabel imagePathLabel = null;
	private JTextField imagePathField = null;
	private JLabel wwiNameLabel = null;
	private JTextField wwiNameField = null;
	
	private Container container = null;
	
	private WordWithImage wwi = null;
	
	/**
	 * Construtor da caixa de dialogo
	 * @param dialog A caixa de dialogo que chama este
	 * @param title O titulo desta caixa
	 */
	public AddWordDialog(JDialog dialog, String title) {
		super(dialog, title, true);
		configure();
		addComponents();
	}
	
	/**
	 * Retorna o wwi por este selecionado
	 * @return O wwi selecionado
	 */
	public WordWithImage getWWI() {
		return wwi;
	}
	
	/**
	 * Configura as caracteristicas gerais deste dialog
	 *
	 */
	private void configure() {
		container = getContentPane();
		container.setLayout(null);
		this.setSize(420, 210);
		this.setResizable(false);
		this.setLocation(100,100);
	}
	
	/**
	 * Adiciona os componentes ao container
	 *
	 */
	private void addComponents() {

		container.add(getImagePathField());
		container.add(getImagePathLabel());
		container.add(getWWINameField());
		container.add(getWWINameLabel());
		container.add(getImageLabel());
		container.add(getOkButton());
		container.add(getCancelButton());
		container.add(getSelectButton());
	}
	
	/**
	 * Atualiza o label que contem a imagem
	 *
	 */
	private void updateImageLabel() {
		File file = new File(imagePathField.getText());
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(file.toURL());
		
		}  catch (IOException e) {
			//TODO - doing nothing
		}
		if(img == null) {
			img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		}
		imageLabel.setIcon(new ImageIcon(img.getScaledInstance(100, 100, BufferedImage.SCALE_FAST)));
	}
	
	/**
	 * Retorna o label que contem a imagem
	 * @return o label que contem a imagem.
	 */
	private JLabel getImageLabel() {
		if(imageLabel == null) {
			imageLabel = new JLabel();
			imageLabel.setBounds(10, 10, 100, 100);
			updateImageLabel();
		}
		return imageLabel;
		
	}
	
	/**
	 * Retorna o label que contem o nome do wwi
	 * @return o label que contem o nome do wwi.
	 */
	private JLabel getWWINameLabel() {
		if(wwiNameLabel == null) {
			wwiNameLabel = new JLabel("Palavra: ");
			wwiNameLabel.setBounds(130, 15, 70, 30);
		}
		return wwiNameLabel;
	}
	
	/**
	 * Retorna o label que contem o caminho a imagem <br> Caso ele esteja com valor nulo, inicializa-o
	 * @return o label que contem o caminho a imagem.
	 */
	private JLabel getImagePathLabel() {
		if(imagePathLabel == null) {
			imagePathLabel = new JLabel("Imagem: ");
			imagePathLabel.setBounds(130, 55, 70, 30);
		}
		return imagePathLabel;
	}
	
	/**
	 * Retorna o field que contem o nome do wwi <br> Caso ele esteja com valor nulo, inicializa-o
	 * @return o field que contem o nome do wwi.
	 */
	private JTextField getWWINameField() {
		if(wwiNameField == null) {
			wwiNameField = new JTextField();
			wwiNameField.setEditable(true);
			wwiNameField.setBounds(200, 15, 200, 30);
		}
		return wwiNameField;
	}
	
	/**
	 * Retorna o field que contem o caminho a imagem <br> Caso ele esteja com valor nulo, inicializa-o
	 * @return o field que contem o caminho a imagem.
	 */
	private JTextField getImagePathField() {
		if(imagePathField == null) {
			imagePathField = new JTextField();
			imagePathField.setEditable(false);
			imagePathField.setBounds(200, 55, 200, 30);
		}
		return imagePathField;
	}
	
	/**
	 * Retorna o okButton <br> Caso eles esteja com valor nulo, inicializa-o
	 * @return  Retorna o okButton.
	 */
	private JButton getOkButton() {
		if(okButton == null) {
			okButton = new JButton("Adicionar");
			okButton.addActionListener(this);
			okButton.setBounds(10, 130, 100, 30);
		}
		return okButton;
	}
	
	/**
	 * Retorna o cancelButton. <br> Caso eles esteja com valor nulo, inicializa-o
	 * @return  Retorna o cancelButton.
	 */
	private JButton getCancelButton() {
		if(cancelButton == null) {
			cancelButton = new JButton("Cancelar");
			cancelButton.addActionListener(this);
			cancelButton.setBounds(120, 130, 100, 30);
		}
		return cancelButton;
	}
	
	/**
	 * Retorna o selectButton (Botao que permite selecionar a imagem)<br> Caso eles esteja com valor nulo, inicializa-o
	 * @return  o selectButton
	 */
	private JButton getSelectButton() {
		if(selectButton == null) {
			selectButton = new JButton("Selecionar Imagem");
			selectButton.addActionListener(this);
			selectButton.setBounds(230, 130, 170, 30);
		}
		return selectButton;
	}
	
	/**
	 * Configura as acoes determinadas pelos botoes
	 */
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == cancelButton) {
			this.dispose();
			
		} else if(arg0.getSource() == selectButton) {
			JFileChooser jfc = new JFileChooser();
			
			jfc.setFileFilter(new ImageFilter());
			
			
			jfc.setSize(200, 200);
			jfc.setMultiSelectionEnabled(false);
				
			jfc.showDialog(this, "Escolha a imagem");
		
			File selectedFile = jfc.getSelectedFile();
			
			if (selectedFile != null) {
				try {
					BufferedImage img = ImageIO.read(selectedFile.toURL());
					if(img == null) {
						throw new IOException();
					}
					imagePathField.setText(selectedFile.getAbsolutePath());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this,"O arquivo nao corresponde a uma imagem", "Erro",
							JOptionPane.ERROR_MESSAGE);
					imagePathField.setText("");
				}
				
				
				updateImageLabel();
			}
			
			
		} else if(arg0.getSource() == okButton) {
			String imagePath = imagePathField.getText();
			String name = wwiNameField.getText();
			if(!name.equals("") && !imagePath.equals("") && (name!=null) && (imagePath!=null)) {
				wwi = new WordWithImage(imagePath, name);
				this.setEnabled(false);
				this.setVisible(false);
				
			} else {
				JOptionPane.showMessageDialog(this, "Preencha os campos corretamente", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
		
	}
	
	/**
	 * Constroi um AddWordDialog, e retorna a WordWithImage gerada por ele
	 * @param dialog o JDialog pai do AddWordDialog
	 * @param title o titulo a ser exibido no AddWordDialog
	 * @return a WordWithImage gerada
	 */
	public static WordWithImage showAddWWIDialog(JDialog dialog, String title) {
		AddWordDialog awd = new AddWordDialog(dialog, title);
		awd.setEnabled(true);
		awd.setVisible(true);
		
		return(awd.getWWI());	
	}
	
}
