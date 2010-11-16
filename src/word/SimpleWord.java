package word;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import util.MenuSelectable;

/**
 * Classe que representa uma palavra simples<br> Sem embaralhamento ou remocao de letras Implementa GuessStructure, Serializable, MenuSelectable<br>
 * @author  abmargb thiagoss
 */
public class SimpleWord implements GuessStructure, Serializable, MenuSelectable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int LEVEL_CODE = 1;
	
	protected String word; 
	protected String visibleWord;
	protected String keySequence;
	protected String solvedWord;
	protected BufferedImage image;
	protected int actualPosition = 0;
	
	/**
	 * Construtor do SimpleWord
	 * @param word a palavra a ser adivinhada
	 * @param image a imagem associada a palavra
	 */
	public SimpleWord(String word, BufferedImage image) {
		if(image == null) {
			throw new NullPointerException();
		}
		actualPosition = 0;
		this.image = image;
		this.word = word.toUpperCase();
		this.keySequence = this.word; 
		this.visibleWord = this.word;
		this.solvedWord = "";
	}
	
	/**
	 * Construtor que recebe um WordWithImage, encapsula os 
	 * parametros no WordWithImage
	 * @param wwi o WordWithImage com os dados pra gerar o SimpleWord
	 * @throws IOException
	 */
	public SimpleWord(WordWithImage wwi) throws IOException {
		this(wwi.getWord(), wwi.getImage());
	}
	
	/*
	 *  (non-Javadoc)
	 * @see word.GuessStructure#tryKey(char)
	 */
	public boolean tryKey(char c) {
		c = (c + "").toUpperCase().charAt(0);
		
		if(getNextKey()== c) {
			actualPosition++;
			if(visibleWord.length() > 1) {
				visibleWord = visibleWord.substring(1);
			} else {
				visibleWord = "";
			}
			solvedWord += c;
			return(true);
		} else {
			return(false);
		}
	}
	
	/*
	 *  (non-Javadoc)
	 * @see word.GuessStructure#getNextKey()
	 */
	public char getNextKey() {
		if(actualPosition < keySequence.length()) {
			return(keySequence.charAt(actualPosition));
		} else {
			return '*'; 
		}
	}
	
	/*
	 *  (non-Javadoc)
	 * @see word.GuessStructure#getWord()
	 */
	/**
	 * @return  Returns the word.
	 */
	public String getWord() {
		return (word);
	}
	
	/*
	 *  (non-Javadoc)
	 * @see word.GuessStructure#isOver()
	 */
	public boolean isOver() {
		return(actualPosition >= keySequence.length());
	}
	
	/*
	 *  (non-Javadoc)
	 * @see word.GuessStructure#getVisibleWord()
	 */
	/**
	 * @return  Returns the visibleWord.
	 */
	public String getVisibleWord() {
		return (visibleWord);
	}

	/*
	 *  (non-Javadoc)
	 * @see word.GuessStructure#getImage()
	 */
	/**
	 * @return  Returns the image.
	 */
	public BufferedImage getImage() {
		return (image);
	}


	/*
	 *  (non-Javadoc)
	 * @see word.GuessStructure#getSolvedWord()
	 */
	/**
	 * @return  Returns the solvedWord.
	 */
	public String getSolvedWord() {
		return solvedWord;
	}
	
	/**
	 * Substitui o caractere na posicao index pelo newChar
	 * @param text a String onde o char vai ser substituido
	 * @param index o indice do char a ser substituido
	 * @param newChar o char que vai ser colocado
	 * @return a String modificada
	 */
	public static String substituteChar(String text, int index, char newChar) {
		if(index <= 0) {
			if(text.length() > 1) {
				return(newChar + text.substring(1));
			}
			return("" + newChar);
		} else {
			StringBuffer output = new StringBuffer();
			output.append(text.substring(0, index));
			output.append(newChar);
			if(index + 1 < text.length()) {
				output.append(text.substring(index + 1));
			}
			return(output.toString());
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see util.MenuSelectable#getMenuName()
	 */
	public String getMenuName() {
		return "Palavra Simples";
	}
	
	/*
	 *  (non-Javadoc)
	 * @see word.GuessStructure#getLevelCode()
	 */
	public int getLevelCode() {
		return LEVEL_CODE;
	}

	
}
