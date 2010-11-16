package tests;

import java.io.IOException;
import junit.framework.TestCase;
import word.WordWithImage;

/**
 * Classe que testa os metodos de word.WordWithImage
 * @author  abmargb, thiagoss
 */
public class WordWithImageTest extends TestCase {

	WordWithImage wwi1 = null;
	WordWithImage wwi2 = null;
	
	/**
	 * Roda os testes
	 * @param args
	 */
	public static void main(String[] args) {
		junit.swingui.TestRunner.run(WordWithImageTest.class);
	}

	/**
	 * Inicializa as variaveis necessarias ao teste
	 */
	public void setUp() throws IOException, ClassNotFoundException {
		wwi1 = WordWithImage.getRandomWWI();
		wwi2 = WordWithImage.getRandomWWI();
	}
	
	/**
	 * Testa o construtor WordWithImage(String, String)
	 */
	public void testWordWithImage() {
		WordWithImage wwi = new WordWithImage("dois", "tres");
		assertEquals(wwi.getWord(), "tres");
		assertEquals(wwi.getImageFilePath(), "dois");
	}

	/**
	 * Testa o metodo setWord(String)
	 */
	public void testSetWord() {
		wwi1.setWord("word");
		assertEquals(wwi1.getWord(), "word");
	}

	/**
	 * Testa o metodo setImageFilePath(String)
	 */
	public void testSetImageFilePath() {
		wwi1.setFilePath(wwi2.getFilePath());
		assertEquals(wwi1.getFilePath(), wwi2.getFilePath());
	}


	/**
	 * Testa o metodo getRandomWWI()
	 */
	public void testGetRandomWWI() throws IOException, ClassNotFoundException {
		for(int i = 0; i < 1000; i++) {
			WordWithImage.getRandomWWI();
		}
	}

	/**
	 * Testa o metodo getMenuName()
	 */
	public void testGetMenuName() {
		assertEquals(wwi2.getMenuName(), wwi2.getWord());
	}

}
