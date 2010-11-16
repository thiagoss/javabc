package tests;

import java.awt.image.BufferedImage;
import java.io.IOException;
import junit.framework.TestCase;
import word.SimpleWord;
import word.WordWithImage;

/**
 * Classe que testa os metodos de word.SimpleWord
 * @author  abmargb, thiagoss
 */
public class SimpleWordTest extends TestCase {

	SimpleWord sw = null;
	
	/**
	 * Roda os testes
	 * @param args
	 */
	public static void main(String[] args) {
		junit.swingui.TestRunner.run(SimpleWordTest.class);
	}
	
	/**
	 * Inicializa as variaveis necessarias ao teste
	 */
	public void setUp() throws IOException, ClassNotFoundException {
		sw = new SimpleWord(WordWithImage.getRandomWWI());
	}

	/**
	 * Testa o metodo substituteChar(String, int, char)
	 */
	public void testSubstituteChar() {
		assertEquals("th_ago", SimpleWord.substituteChar("thiago", 2, '_'));
		assertEquals("_hiago", SimpleWord.substituteChar("thiago", 0, '_'));
		assertEquals("thiag_", SimpleWord.substituteChar("thiago", "thiago".length() - 1, '_'));
	}
	
	/**
	 * Testa o construtor de SimpleWord
	 *
	 */
	public void testConstructor() {
		SimpleWord sWord = null;
		try {
			sWord = new SimpleWord("Teste", null);
			fail();
		} catch (NullPointerException e) {
			
		}
		
		BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		sWord = new SimpleWord("Teste", image);
		assertEquals("TESTE", sWord.getWord());
		assertEquals('T', sWord.getNextKey());
		assertEquals(image, sWord.getImage());
		
	}
	
	/**
	 * Testa o metodo tryKey(char)
	 *
	 */
	public void testTryKey() {
		SimpleWord sw = new SimpleWord("Nome", new BufferedImage(1, 1, 1));
		assertTrue(sw.tryKey('n'));
		assertTrue(sw.tryKey('O'));
		assertTrue(sw.tryKey('M'));
		assertFalse(sw.tryKey('n'));
		assertFalse(sw.tryKey('M'));
		assertFalse(sw.tryKey('O'));
		assertTrue(sw.tryKey('e'));
	}

}
