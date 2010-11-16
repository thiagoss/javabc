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
 * Caixa de dialogo para administração de jogadores
 * Extende ListMenuDialog
 * @author abmargb, thiagoss
 */
public class PlayerManagerDialog extends ListMenuDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -174587266428779136L;
	private JButton buttonSelect = null;
	private JButton buttonBack = null;
	private JButton buttonNewPlayer = null;
	private JButton buttonRemove = null;
	private JLabel titleLabel = null;
	
	
	/**
	 * Construtor do PlayerManagerDialog
	 * @param f O frame que chama esta caixa de dialogo
	 * @param items O itens a serem incluido na lista de players
	 */
	public PlayerManagerDialog(JFrame f, MenuSelectable[] items) {
		super(f, "Gerenciamento de Jogadores", items);
		this.setResizable(false);
		initButtons();
		initLabel();
	}
	
	/**
	 * Inicializa os botões
	 *
	 */
	public void initButtons() {
		buttonSelect = new JButton("Ver estatísticas");
		buttonSelect.setBounds(340, 50, 150, 30);
		buttonSelect.setVisible(true);
		buttonSelect.addActionListener(this);
		container.add(buttonSelect);
		
		buttonBack = new JButton("Voltar");
		buttonBack.setBounds(340, 100, 150, 30);
		buttonBack.setVisible(true);
		buttonBack.addActionListener(this);
		container.add(buttonBack);
		
		buttonNewPlayer = new JButton("Novo Jogador");
		buttonNewPlayer.setBounds(340, 150, 150, 30);
		buttonNewPlayer.setVisible(true);
		buttonNewPlayer.addActionListener(this);
		container.add(buttonNewPlayer);
		
		buttonRemove = new JButton("Apagar Jogador");
		buttonRemove.setBounds(340, 200, 150, 30);
		buttonRemove.setVisible(true);
		buttonRemove.addActionListener(this);
		container.add(buttonRemove);
	}
	
	/**
	 * Inicializa os labels
	 *
	 */
	public void initLabel() {
		titleLabel = new JLabel("Escolha uma opcao");
		titleLabel.setBounds(20, 15, 200, 30);
		container.add(titleLabel);
	}

	/**
	 * Mostra essa caixa de dialogo.<br>
	 * Inicializa a lista com todos players.
	 * @param f O frame que chama este dialog
	 */
	public static void showDialog(JFrame f){
		ArrayList<Player> players = null;
		PlayerManagerDialog dialog;
		
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
		
		dialog = new PlayerManagerDialog(f, menuItems);
		dialog.setVisible(true);
		dialog.setLocation(150, 150);

	}
	
	
	/**
	 * Configura as acoes determinadas pelos botoes
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == (buttonSelect)) {
			Player p = (Player)list.getSelectedValue();
			PlayerStatusDialog psd = new PlayerStatusDialog(this, "Estatisticas");
			psd.setTextFieldPlayerName(p.getName());
			psd.setTextFieldLogin(p.getLogin());
			psd.setPlrRights(p.getRights());
			psd.setPlrWrongs(p.getWrongs());
			psd.setVisible(true);
			psd.setEnabled(true);
			
		} else if(e.getSource() == (buttonBack)) {
			this.dispose();
			
		} else if(e.getSource() == buttonNewPlayer) {
			Player plr = PlayerCreationDialog.createPlayerDialog(this);
			
				try {
					plr.createPlayerFile();
					model.addElement(plr);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LoginExistsException e1) {
					
					JOptionPane.showMessageDialog(null, "Login de jogador ja existente, crie outro", "Erro",
							JOptionPane.ERROR_MESSAGE);
					plr = null;
				}
			
		} else if(e.getSource() == buttonRemove) {
			int index = list.getSelectedIndex();
			if(index == -1) return;
			Player p = (Player)model.get(index);
			int option = JOptionPane.showConfirmDialog(this,
					"Gostaria de apagar o jogador \"" + p.getLogin()+ "\"?",
					"Apagar Jogador", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION) {
				model.remove(index);
				p.removePlayer();
				
			}
			
			
		}
			
	}
	
	

}
