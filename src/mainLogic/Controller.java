package mainLogic;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import player.Player;
import word.GuessStructure;
import word.MissingCharWord;
import word.ScrambledWord;
import word.SimpleWord;
import word.WordWithImage;

/**
 * Classe que representa o controlador do jogo <br> Faz o intermedio entre a logica e a UI,  controlando o todas as instâncias do jogo corrente.
 * @author  abmargb, thiagoss
 */
public class Controller {

	public static final int LEVEL_STARTING_INDEX = 1;
	public static final int SIMPLE_WORD = 1;
	public static final int MISSING_CHAR_WORD = 2;
	public static final int SCRAMBLED_WORD = 3;
	public static final int NUMBER_OF_LEVELS = 3;
	
	private GuessStructure word;
	private Player player;
	private boolean onGame;
	private int level;
	
	/**
	 * Contrutor do controlador
	 * Inicializa o Player e a palavra(GuessStructure) para null
	 * (Nao inicializa nenhuma jogo automaticamente)
	 */
	public Controller() {
		word = null;
		player = null;
		onGame = false;
		level = 1;
	}
	
	/**
	 * Verifica se existe um jogo em andamento
	 * @return true caso exista
	 */
	public boolean onGame() {
		return onGame;
	}
	
		
	/**
	 * Inicia um novo jogo, com o Player e o level especificados <br>
	 * Encerra o jogo anterior (caso exista) salvando as estatisticas do Player
	 * @param p o Player que vai jogar
	 * @param level o nivel do jogo
	 */
	public void startNewGame(Player p, int level) {
		if(player != null) {
			closeGame();
		}
		player = p;
		this.level = level;
		loadNextWord();
		onGame = true;
	}
	
	/**
	 * Modifica o Player que esta jogando
	 * @param p  o novo Player
	 */
	public void setPlayer(Player p) {
		player = p;
	}
	
	/**
	 * Retorna o Player que esta jogando
	 * @return  Player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Retorna a imagem da palavra que esta sendo usada no jogo
	 * num tamanho solicitado
	 * @param imageWidth a largura da imagem
	 * @param imageHeight a altura da imagem
	 * @return a imagem no tamanho solicitado
	 */
	public ImageIcon getImageIcon(int imageWidth, int imageHeight) {
		return new ImageIcon(word.getImage().getScaledInstance(imageWidth, imageHeight, BufferedImage.SCALE_SMOOTH));
	}
	
	/**
	 * Retorna a parte da palavra que eh exibida
	 * ao usuario
	 * @return String
	 */
	public String getVisibleWord() {
		return word.getVisibleWord();
	}
	
	/**
	 * Chamado quando o usuario digita um caractere,
	 * verifica se ele acertou ou nao. <br>
	 * Tambem altera os acertos e erros do jogador 
	 * @param c o caractere digitado
	 * @return true caso o usuario acerte
	 */
	public boolean tryChar(char c) {
		c = Character.toUpperCase(c);
		if(!word.isOver()) {
		
			if(word.tryKey(c)) {
				player.addRight();
				return true;
			} else {
				player.addWrongs();
			}
		} 
		return false;
		
	}

	/**
	 * Carrega a proxima palavra para o jogo
	 *
	 */
	public void loadNextWord() {
		WordWithImage wwi = null;
		try {
			wwi = WordWithImage.getRandomWWI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(wwi != null) {
			word = loadGuessStructure(wwi, level);
		} else {
			onGame = false;
			JOptionPane.showMessageDialog(null, "Houve uma falha ao carregar o arquivo." +
					"Cheque se existem palavras no banco do jogo",
					"Erro!", JOptionPane.ERROR_MESSAGE);
		}
		onGame = true;
		
	}
	
	/**
	 * Carrega uma palavra para o jogo a partir de um WordWithImage
	 * @param wwi contem as informacoes da palvra e a imagem
	 * @param level o nivel de jogo
	 * @return a GuessStructure a ser usada no jogo
	 */
	public static GuessStructure loadGuessStructure(WordWithImage wwi, int level) {
		try {
			switch(level) {
				case Controller.SIMPLE_WORD:
					return new SimpleWord(wwi);
		
				case Controller.MISSING_CHAR_WORD:
					return new MissingCharWord(wwi);
		
				case Controller.SCRAMBLED_WORD:
					return new ScrambledWord(wwi);
		
				default:
					return new SimpleWord(wwi);
			}
		} catch (IOException e) {
			//TODO
			e.printStackTrace();
		}
		return new SimpleWord("ERROR!!!", new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB));
	}

	/**
	 * Retorna a String a ser exibida como a parte ja solucionada
	 * da palavra
	 * @return String
	 */
	public String getSolvedWord() {
		return word.getSolvedWord();
	}

	/**
	 * Verifica se a palavra da vez ja foi finalizada
	 * @return true caso tenha sido.
	 */
	public boolean wordIsOver() {
		return word.isOver();
	}
	
	/**
	 * Encerra o jogo atual<br>
	 * Salva as estatisticas do jogador num arquivo
	 */
	public void closeGame() {
		try {
			player.savePlayer();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "O arquivo do jogador nao pode ser gravado",
					"Erro", JOptionPane.ERROR_MESSAGE );
		}
		onGame = false;	
	}
	

}
