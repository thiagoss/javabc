package tests;

import word.MissingCharWord;
import junit.framework.TestCase;

/**
 * Classe que testa os metodos de word.MissingCharWord
 * @author abmargb, thiagoss
 *
 */
public class MissingCharWordTest extends TestCase {

	/**
	 * Roda os testes
	 * @param args
	 */
	public static void main(String[] args) {
		junit.swingui.TestRunner.run(MissingCharWordTest.class);
	}

	/**
	 * Testa o metodo for selectRandomChar(String)
	 */
	public void testSelectRandomChar() {
		for(int i = 0; i < 600; i++) {
			int t = MissingCharWord.selectRandomChar("thiago");
			assertTrue(t < 6);
		}
	}
		
		
}
