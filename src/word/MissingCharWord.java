package word;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Classe que representa uma palavra a ser adivinhada.<br>
 * Extende SimpleWord. Neste caso, uma letra sera ocultada e o 
 * usuario deve adivinhar qual eh esta letra.
 * @author thiagoss
 *
 */
public class MissingCharWord extends SimpleWord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int LEVEL_CODE = 2;

	/**
	 * Contrutor de MissingCharWord
	 * @param word a palavra ser usada para a adivinhacao
	 * @param image a imagem associada a este MissingCharWord
	 */
	public MissingCharWord(String word, BufferedImage image) {
		super(word, image);
		int selectedChar = selectRandomChar(word);
		this.keySequence = "" + this.word.charAt(selectedChar);
		this.visibleWord = super.substituteChar(this.word, selectedChar, '_');
		this.solvedWord = visibleWord;
	}
	
	/**
	 * Construtor que abstrai os parametros num WordWithImage
	 * @param wwi WordWithImage com a palavra e a imagem associada a ela.
	 * @throws IOException
	 */
	public MissingCharWord(WordWithImage wwi) throws IOException {
		super(wwi.getWord(), wwi.getImage());
		int selectedChar = selectRandomChar(word);
		this.keySequence = "" + this.word.charAt(selectedChar);
		this.visibleWord = super.substituteChar(this.word, selectedChar, '_');
		this.solvedWord = visibleWord;
	}
	
	/**
	 * Escolhe uma letra dentro da String text
	 * @param text a String para escolher o char
	 * @return o indice do char escolhido
	 */
	public static int selectRandomChar(String text) {
		return(int) (Math.floor(Math.random() * text.length()));
	}
	
	/*
	 *  (non-Javadoc)
	 * @see word.GuessStructure#tryKey(char)
	 */
	public boolean tryKey(char c) {
		c = (c + "").toUpperCase().charAt(0);
		
		if(getNextKey()== c) {
			actualPosition++;
			solvedWord = word;
			return(true);
		} else {
			return(false);
		}	
	}
	 /*
	  *  (non-Javadoc)
	  * @see util.MenuSelectable#getMenuName()
	  */
	public String getMenuName() {
		return "Caractere ausente";
	}

	/*
	 *  (non-Javadoc)
	 * @see word.GuessStructure#getLevelCode()
	 */
	public int getLevelCode() {
		return(LEVEL_CODE);
	}
	
}
