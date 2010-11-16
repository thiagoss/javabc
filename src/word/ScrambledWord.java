package word;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Classe que representa uma palavra embaralhada<br>
 * Extende SimpleWord. A palavra e embaralhada e cabe ao usuario
 * digitar a palavra correspondente.
 * @author abmargb, thiagoss
 *
 */
public class ScrambledWord extends SimpleWord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int LEVEL_CODE = 3;

	/**
	 * Constroi o ScrambledWord a partir de uma palavra e uma imagem
	 * @param word a palavra
	 * @param image a imagem
	 */
	public ScrambledWord(String word, BufferedImage image) {
		super(word, image);
		this.visibleWord = scrabble(this.word);
	
	}
	
	/**
	 * Construtor que abstrai os parametros de passagem num
	 * WordWithImage
	 * @param wwi o WordWithImage contendo o nome e a imagem
	 * @throws IOException
	 */
	public ScrambledWord(WordWithImage wwi) throws IOException {
		super(wwi.getWord(), wwi.getImage());
		this.visibleWord = scrabble(this.word);
	}

	/*
	 *  (non-Javadoc)
	 * @see word.GuessStructure#tryKey(char)
	 */
	public boolean tryKey(char c) {
		if(getNextKey()== c) {
			actualPosition++;
			if(visibleWord.length() > 1) {
				visibleWord = substituteChar(visibleWord, visibleWord.indexOf(c), '_');
			} else {
				visibleWord = "";
			}
			solvedWord += c;
			return(true);
		} else {
			return(false);
		}
	}
	
	/**
	 * Mistura as letras da string Text
	 * @param text a String que vai ter as letras embaralhadas
	 * @return a String embaralhada
	 */
	public static String scrabble(String text) {
		if (checkOneCharWord(text)) {
			return text;
		}
		
		String tempString;
		do {
			char[] characters = new char[text.length()];
			text.getChars(0, text.length(), characters, 0);
			
			for(int i = 0; i < text.length(); i++) {
				int selectedIndex = i + (int)(Math.random() * (text.length() - i)); 
				
				char aux = characters[i];
				characters[i] = characters[selectedIndex];
				characters[selectedIndex] = aux;
				
			}
			tempString = new String(characters);
		} while (text.equals(tempString));
		
		return(tempString);
	}
	
	/*
	 *  (non-Javadoc)
	 * @see util.MenuSelectable#getMenuName()
	 */
	public String getMenuName() {
		return "Palavra Embaralhada";
	}
	
	/*
	 *  (non-Javadoc)
	 * @see word.GuessStructure#getLevelCode()
	 */
	public int getLevelCode() {
		return(LEVEL_CODE);
	}

	/**
	 * Verifica se a String contem ou nao letras, ou
	 * eh formada por apenas letras iguais
	 * @param word
	 * @return true se houver, false caso contrario
	 */
	public static boolean checkOneCharWord(String word) {
		if(word.length() == 0) {
			return true;
		}
		char c = word.charAt(0);
		for(int i = 1; i < word.length(); i++) {
			if(word.charAt(i) != c) {
				return false;
			}
		}
		return true;
	}
	
}

