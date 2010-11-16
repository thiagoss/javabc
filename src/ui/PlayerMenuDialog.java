package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import player.LoginExistsException;
import player.Player;
import util.MenuSelectable;

/**
 * Caixa de dialogo para administração de jogadores na seleção de novo jogo
 * Extende ListMenuDialog
 * @author abmargb, thiagoss
 */
public class PlayerMenuDialog extends ListMenuDialog implements ActionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7617138048253788989L;
	private JButton buttonSelect = null;
	private JButton buttonCancel = null;
	private JButton buttonNewPlayer = null;
	private JLabel titleLabel = null;
	
	private Player playerSelected = null;
	
	/**
	 * Construtor do PlayerMenuDialog
	 * @param f O frame que chama esta caixa de dialogo
	 * @param items O itens a serem incluido na lista de players
	 */
	public PlayerMenuDialog(JFrame f, MenuSelectable[] items) {
		super(f, "Menu de Jogadores", items);
		playerSelected = null;
		this.setResizable(false);
		initButtons();
		initLabel();
	}
	
	/**
	 * Retorna o jogador selecionado
	 * @return Retorna o jogador selecionado.
	 */
	public Player getPlayerSelected() {
		return playerSelected;
	}
	
	/**
	 * Inicializa os botões
	 *
	 */
	public void initButtons() {
		buttonSelect = new JButton("OK");
		buttonSelect.setBounds(340, 50, 150, 30);
		buttonSelect.setVisible(true);
		buttonSelect.addActionListener(this);
		container.add(buttonSelect);
		
		buttonCancel = new JButton("Cancelar");
		buttonCancel.setBounds(340, 100, 150, 30);
		buttonCancel.setVisible(true);
		buttonCancel.addActionListener(this);
		container.add(buttonCancel);
		
		buttonNewPlayer = new JButton("Novo Jogador");
		buttonNewPlayer.setBounds(340, 150, 150, 30);
		buttonNewPlayer.setVisible(true);
		buttonNewPlayer.addActionListener(this);
		container.add(buttonNewPlayer);
		
	}
	
	/**
	 * Inicializa os labels
	 *
	 */
	public void initLabel() {
		titleLabel = new JLabel("Escolha seu jogador");
		titleLabel.setBounds(20, 15, 200, 30);
		container.add(titleLabel);
	}

	/**
	 * Mostra essa caixa de dialogo.<br>
	 * Inicializa a lista com todos players.
	 * @param f O frame que chama este dialog
	 */
	public static Player showDialog(JFrame f){
		ArrayList<Player> players = null;
		PlayerMenuDialog dialog;
		
		try {
			players = Player.readAllPlayers();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		MenuSelectable[] menuItems = new MenuSelectable[players.size()];
		
		for(int i = 0; i < menuItems.length; i++) {
			menuItems[i] = players.get(i);
		}
		
		dialog = new PlayerMenuDialog(f, menuItems);
		dialog.setVisible(true);
		dialog.setLocation(150, 150);

		return dialog.getPlayerSelected();
	}
	
	/**
	 * Configura as acoes determinadas pelos botoes
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == (buttonSelect)) {
			Player p = (Player)list.getSelectedValue();
			if(p == null) {
				
			} else {
				playerSelected = new Player(p.getName(), p.getLogin(), p.getWrongs(), p.getRights());
				this.setEnabled(false);
				this.setVisible(false);
				this.setModal(false);
				
			}
			
		} else if(e.getSource() == (buttonCancel)) {
			playerSelected = null;
			this.dispose();
			
		} else if(e.getSource() == buttonNewPlayer) {
			playerSelected = PlayerCreationDialog.createPlayerDialog(this);
			
				try {
					playerSelected.createPlayerFile();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LoginExistsException e1) {
					
					JOptionPane.showMessageDialog(null, "Login de jogador ja existente, crie outro", "Erro",
							JOptionPane.ERROR_MESSAGE);
					playerSelected = null;
				}
			
				model.add(model.getSize(), playerSelected);

			
		} 	
		
	
	}
	
	

}
