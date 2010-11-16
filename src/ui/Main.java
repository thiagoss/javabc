package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import mainLogic.Controller;
import player.Player;

/**
 * Frame principal do programa, onde o jogo interage com o jogador<br>
 * Também possui a barra de ferramentas do jogo. <br>
 * Através deste que o jogo (controlador) é iniciado
 * @author abmargb, thiagoss
 *
 */
public class Main extends JFrame implements KeyListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String fontName = "monospaced";
	
	private JLabel image;
	private JTextField word;
	private JTextField typedWord;
	private JMenuBar menuBar;
	private JMenu toolsMenu;
	private JMenu gameMenu;
	private JMenu playerMenu;
	private JMenu helpMenu;
	private JMenuItem playerMI;
	private JMenuItem wordMI;
	private JMenuItem newGameMI;
	private JMenuItem exitGameMI;
	private JMenuItem playerStatusMI;
	private JMenuItem helpMI;
	private JMenuItem aboutMI;
	private Container container;
	private final Font bigFont = new Font(fontName, Font.BOLD, 32);
	
	private PlayerStatusPanel plrStatPanel; 
		
	private Controller controller;
	
	private final Color playerStatusPanelTitleColor = Color.BLACK;
	private final Color playerStatusPanelTextColor = new Color(91, 133, 255);
	private final Font playerPanelTitleFont = new Font(fontName, Font.BOLD, 20);
	private final Font playerPanelTextFont = new Font(fontName, Font.PLAIN, 20);
	
	/**
	 * Configura os parametros basicos da interface
	 *
	 */
	private void configure() {
		
		this.setTitle("JavABC");

		setSize(700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container = getContentPane();
		container.setLayout(null);
		

	}
	
	/**
	 * configura o KeyListener da interface
	 *
	 */
	private void configureKeyListener() {
		typedWord.addKeyListener(this);
	}
	
	/**
	 * configura o WindowListener da interface
	 *
	 */
	private void configureWindowListener(){
		addWindowListener(this);
	}
	
	/**
	 * inicializa os labels da interface
	 *
	 */
	private void initLabels(){
		image = new JLabel(new ImageIcon(new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB)));
		image.setBounds(30, 70, 300, 300);
		image.setFocusable(false);
		container.add(image);
	}
	
	/**
	 * inicializa os text fields da interface
	 *
	 */
	private void initTextFields() {
		word = new JTextField();
		word.setBounds(350, 70, 350, 50);
		word.setFont(bigFont);
		word.setEditable(false);
		word.setForeground(Color.red);
		word.setBorder(null);
		word.setBackground(null);
		word.setFocusable(false);
		container.add(word);
		
		typedWord = new JTextField();
		typedWord.setBounds(350, 320, 350, 50);
		typedWord.setFont(bigFont);
		typedWord.setEditable(false);
		typedWord.setBackground(null);
		typedWord.setForeground(new Color(0, 200, 0));
		typedWord.setBorder(null);
		typedWord.setFocusable(true);
		container.add(typedWord);
	}
	
	/**
	 * Inicializa os paineis da interface
	 *
	 */
	private void initPanels() {
		plrStatPanel = new PlayerStatusPanel(playerPanelTitleFont, playerPanelTextFont, 
				playerStatusPanelTitleColor, playerStatusPanelTextColor);
		plrStatPanel.setBounds(350,150,250,200);
		plrStatPanel.setPlayerName("...");
		container.add(plrStatPanel);
	}
	
	/**
	 * Inicializa os menus da interface
	 *
	 */
	private void initMenus() {
		//menu Jogo (Novo jogo, sair)
		newGameMI = new JMenuItem("Novo jogo");
		newGameMI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Player playerSelected = selectPlayer();
				if(playerSelected != null) {
					int level = selectLevel();
					
					if(level != 0) {
						controller.startNewGame(playerSelected, level);
					}
						
				}
				
				updateUIData();
			}
			
		});
		exitGameMI = new JMenuItem("Sair...");
		exitGameMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeApplication();				
			}
		});
		gameMenu = new JMenu("Jogo");
		gameMenu.add(newGameMI);
		gameMenu.add(exitGameMI);
		
		//menu Jogador (estatisticas)
		playerStatusMI = new JMenuItem("Estatisticas...");
		playerStatusMI.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				playerStatus();
				
			}
			
		});
		playerMenu = new JMenu("Jogador");
		playerMenu.add(playerStatusMI);
		
		//Menu ferramentas (jogadores, palavras)
		playerMI = new JMenuItem("Jogadores...");
		playerMI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				managePlayers();
				
			}
			
		});
		wordMI = new JMenuItem("Palavras...");
		wordMI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				manageWords();
				
			}
			
		});
		toolsMenu = new JMenu("Ferramentas");
		toolsMenu.add(playerMI);
		toolsMenu.add(wordMI);
		
		aboutMI = new JMenuItem("Sobre...");
		aboutMI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				aboutBox();
				
			}
			
		});
		helpMI = new JMenuItem("Instrucoes");
		helpMI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				helpMenu();
				
			}
			
		});
		
		helpMenu = new JMenu("Ajuda");
		helpMenu.add(aboutMI);
		helpMenu.add(helpMI);
		
		
		
		menuBar = new JMenuBar();
		menuBar.add(gameMenu);
		menuBar.add(playerMenu);
		menuBar.add(toolsMenu);
		menuBar.add(helpMenu);
			
		setJMenuBar(menuBar);
	}
	
	/**
	 * Inicializa a interface e o controlador
	 *
	 */
	public Main() {
		configure();
		initLabels();
		initTextFields();
		initMenus();
		initPanels();
		setInvisible();
		configureKeyListener();
		configureWindowListener();
		
		controller = new Controller();
		
		updateUIData();
	}
	
	/**
	 * Atualiza as palavras da interface <br>
	 * (Palavras esperada e palavra digitada ate o momento)
	 */
	public void updateTextUI() {
		word.setText(controller.getVisibleWord());
		typedWord.setText(controller.getSolvedWord());
	}
	
	/**
	 * Atualiza as palavras e as estatisticas do jogador <br>
	 * Senao não houver jogo corrente, os torna invisivel
	 */
	public void updateUIData() {
		if(controller.onGame()) {
			updateTextUI();
			updateStatsPanel();
			image.setIcon(controller.getImageIcon(300, 300));
			setVisible();
			typedWord.requestFocus();
		} else {
			setInvisible();
		}
		
	}
	
	/**
	 * Atualiza o painel das estatisticas do jogador
	 *
	 */
	public void updateStatsPanel() {
		plrStatPanel.setPlayerName(controller.getPlayer().getLogin());
		plrStatPanel.setRights(controller.getPlayer().getRights());
		plrStatPanel.setWrongs(controller.getPlayer().getWrongs());
	}
	
	/**
	 * Torna os elementos deste frame visiveis
	 *
	 */
	public void setVisible() {
		image.setVisible(true);
		word.setVisible(true);
		typedWord.setVisible(true);
		plrStatPanel.setVisible(true);
	}
	
	/**
	 * Torna os elementos deste frame invisiveis
	 *
	 */
	public void setInvisible() {
		image.setVisible(false);
		word.setVisible(false);
		typedWord.setVisible(false);
		plrStatPanel.setVisible(false);
	}
	
	/**
	 * Roda o jogo
	 * @param args
	 */
	public static void main(String[] args) {
		SplashWindow.getInstance().runSplashScreen();
		Main m = new Main();
		m.setVisible(true);
		
	}

	/**
	 * Configura as ações para eventos de teclado
	 */
	public void keyTyped(KeyEvent e) {
	
		if(controller.tryChar(e.getKeyChar())) {
			updateTextUI();
			try {
				PlayAudio.playAudioKey(e.getKeyChar());
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			try {
				PlayAudio.playMistakeSound();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		updateStatsPanel();
		
		
		if(controller.wordIsOver()) {
			try {
				PlayAudio.playCongratSound();
			} catch (UnsupportedAudioFileException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (LineUnavailableException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			this.update(getGraphics());
			controller.loadNextWord();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			updateUIData();
		}
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Mostra o menu de instruções
	 *
	 */
	public void helpMenu() {
		String LINESEPARATOR = System.getProperty("line.separator");
		String INSTRUCTIONS = 
			"JavABC - Instruções " + LINESEPARATOR + LINESEPARATOR +
			"O JavABC é um programa destinado a auxiliar pais/professores " +
			"na alfabetização de crianças, e simultaneamente, prover uma " +
			"interação da criança com o mundo da computação." + LINESEPARATOR +
			"O jogo possui três modos: Caractere ausente, palavra embaralhada e " +
			"palavra simples. No modo de caractere ausente o jogador deve digitar" +
			" o caratere que falta para a palavra ser completa." + LINESEPARATOR +
			"No modo de palavra emabaralhada, o jogador deve desembaralhar a palavra." + LINESEPARATOR +
			"No modo de palavra simples, basta ao jogador redigitar a palavra original." + LINESEPARATOR +
			"Para comecar um novo jogo, basta clicar em Jogo -> Novo Jogo e escolher um" +
			" jogador e um modo de jogo." + LINESEPARATOR + 
			"Boa Sorte!";
			
		HelpDialog hd = new HelpDialog((Frame)this, "Ajuda", "Instrucoes", INSTRUCTIONS, "Voltar");
		hd.setVisible(true);
		hd.setLocation(100, 50);
	}
	
	/**
	 * Mostra a caixa de dialogo de seleção de jogador
	 * @return O player selecionado
	 */
	public Player selectPlayer() {
		return PlayerMenuDialog.showDialog(this);
	}
	
	/**
	 * Mostra a caixa de dialogo de seleção de level
	 * @return O player selecionado
	 */
	public int selectLevel() {
		return LevelMenuDialog.showDialog(this);
	}
	
	/**
	 * Mostra a janela de administração de palavras
	 *
	 */
	public void manageWords() {
		WordManagerDialog wmd = new WordManagerDialog(this, "Gerenciador de Palavras");
		wmd.setVisible(true);
		wmd.setEnabled(true);
	}
	
	/**
	 * Mostra a janela de administração de jogadores
	 *
	 */
	public void managePlayers() {
		PlayerManagerDialog.showDialog(this);
		
	}
	
	/**
	 * Mostra a janela de status do player corrente <br>
	 * Senao houver um jogo corrente, ele avisa ao usuario 
	 *
	 */
	public void playerStatus() {
		if (controller.onGame()) {
			PlayerStatusDialog psd = new PlayerStatusDialog(this, "Estatisticas");
			psd.setTextFieldPlayerName(controller.getPlayer().getName());
			psd.setTextFieldLogin(controller.getPlayer().getLogin());
			psd.setPlrRights(controller.getPlayer().getRights());
			psd.setPlrWrongs(controller.getPlayer().getWrongs());
			psd.setVisible(true);
			psd.setEnabled(true); 
		} else {
			JOptionPane.showMessageDialog(this, "Nao ha jogo em andamento.", "Aviso", 
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Mostra a caixa de Sobre
	 *
	 */
	public void aboutBox() {
		AboutDialog ad = new AboutDialog(this, "Sobre");
		ad.setVisible(true);
		ad.setEnabled(true);
	}
	
	/**
	 * Fecha a aplicação, encerrando o jogo se existir jogo em andamento
	 *
	 */
	public void closeApplication() {
		if (controller.onGame()) 
			controller.closeGame();
		System.exit(0);
	}

	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent arg0) {
		if (controller.onGame())
			controller.closeGame();
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent arg0) {
//		 TODO Auto-generated method stub
		
	}

	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
