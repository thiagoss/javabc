package tests;

import word.ScrambledWord;
import junit.framework.*;;

/**
 * Classe que testa os metodos de word.ScrambledWord
 * @author abmargb, thiagoss
 *
 */
public class ScrabbledWordTest extends TestCase {

	/**
	 * Roda os testes
	 * @param args
	 */
	public static void main(String[] args) {
		junit.swingui.TestRunner.run(ScrabbledWordTest.class);
	}

	/**
	 * Testa o metodo scrabble(String)
	 */
	public void testScrabble() {
		String test = "thiago";
		String mixed = ScrambledWord.scrabble(test);
		System.out.printf(mixed + "\n");
		assertNotSame(test, mixed);
		
		test = "banana";
		mixed = ScrambledWord.scrabble(test);
		System.out.printf(mixed + "\n");
		assertNotSame(test, mixed);
		
		test = "cavalo";
		mixed = ScrambledWord.scrabble(test);
		System.out.printf(mixed + "\n");
		assertNotSame(test, mixed);
		
		test = "Carruagem";
		mixed = ScrambledWord.scrabble(test);
		System.out.printf(mixed + "\n");
		assertNotSame(test, mixed);
		
		for(int i = 0; i < 1000; i++) {
			test = "casa";
			mixed = ScrambledWord.scrabble(test);
			System.out.printf(mixed + "\n");
			assertNotSame(test, mixed);
		}
	}
	
	/**
	 * Testa o metodo checkOneCharWord(String)
	 */
	public void testCheckOneCharWord() {
		assertTrue(ScrambledWord.checkOneCharWord(""));
		assertTrue(ScrambledWord.checkOneCharWord("a"));
		assertTrue(ScrambledWord.checkOneCharWord("aaa"));
		assertTrue(ScrambledWord.checkOneCharWord("aaaaa"));
		assertFalse(ScrambledWord.checkOneCharWord("abbbbbb"));
		assertFalse(ScrambledWord.checkOneCharWord("ab"));
		
		
	}

}

