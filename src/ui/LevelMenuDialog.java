package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import mainLogic.Controller;
import util.MenuSelectable;
import word.GuessStructure;
import word.WordWithImage;

/**
 * Classe que extende o ListMenuDialog <br>
 * Possui um list para seleção do level pelo usuario
 * Também possui um botão de Select, um botão de Cancel e um titulo
 * @author abmargb, thiagoss
 *
 */
public class LevelMenuDialog extends ListMenuDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7272635574158435632L;
	JButton buttonSelect = null;
	JButton buttonCancel = null;
	JLabel titleLabel = null;
	
	private GuessStructure LevelSelected = null;
	
	/**
	 * Construtor desta caixa de dialogo
	 * @param f O frame que o chama 
	 * @param items Os itens que preencherao a lista
	 */
	public LevelMenuDialog(JFrame f, MenuSelectable[] items) {
		super(f, "Menu de Niveis de Jogo", items);
		initButtons();
		this.setVisible(true);
		this.setLocation(150, 150);
		
	}
	
	/**
	 * Retorna o level selecionado na lista
	 * @return  Retorna o level selecionado
	 */
	public GuessStructure getLevelSelected() {
		return LevelSelected;
	}
	
	/**
	 * Inicializa os botões da caixa
	 *
	 */
	public void initButtons() {
		buttonSelect = new JButton("OK");
		buttonSelect.setBounds(340, 50, 125, 30);
		buttonSelect.setVisible(true);
		buttonSelect.addActionListener(this);
		container.add(buttonSelect);
		
		buttonCancel = new JButton("Cancelar");
		buttonCancel.setBounds(340, 100, 125, 30);
		buttonCancel.setVisible(true);
		buttonCancel.addActionListener(this);
		container.add(buttonCancel);
		
	}

	/**
	 * Configura as acoes determinadas pelos botoes
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonSelect) {
			LevelSelected = (GuessStructure)list.getSelectedValue();
			this.setEnabled(false);
			this.setVisible(false);
			this.setModal(false);
		} else if(e.getSource() == buttonCancel) {
			LevelSelected = null;
			this.dispose();
		}
		
	}
	/**
	 * Mostra esta caixa de dialogo e retorna um codigo correspondente ao level selecionado
	 * @param f O frame que o chama
	 * @return O codigo correspondente ao level selecionado
	 */
	public static int showDialog(JFrame f){
		ArrayList<GuessStructure> levels = new ArrayList<GuessStructure>();
		
		
		for(int i = Controller.LEVEL_STARTING_INDEX; i < Controller.LEVEL_STARTING_INDEX + Controller.NUMBER_OF_LEVELS; i++) {
			try {
				levels.add(Controller.loadGuessStructure(WordWithImage.getRandomWWI(), i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		MenuSelectable[] menuItems = new MenuSelectable[levels.size()];
		for(int i = 0; i < menuItems.length; i++) {
			menuItems[i] = (MenuSelectable)levels.get(i);
		}
		

		
		LevelMenuDialog dialog = new LevelMenuDialog(f, menuItems);

		if (dialog.getLevelSelected() != null) {
			return dialog.getLevelSelected().getLevelCode();
		} else {
			return 0;
		}
	}
	

}
