package tests;

import java.io.File;
import junit.framework.TestCase;
import util.FileHandling;
import util.ImageFilter;

/**
 * Classe que testa os métodos de util.ImageFilter
 * @author  abmargb, thiagoss
 */
public class ImageFilterTest extends TestCase {

	final static String SEPARADOR = FileHandling.SEPARADOR;
	final static String IMAGEDIR = FileHandling.IMAGEDIR;
	ImageFilter imageFilter;
	
	/**
	 * Inicializa o filtro a ser testado
	 */
	public void setUp() {
		imageFilter = new ImageFilter();
	}
	
	/**
	 * Finaliza o filtro testado
	 */
	public void tearDown() {
		imageFilter = null;
	}
	
	/**
	 * Testa o metodo getExtension()
	 * Utiliza imagens da pasta data/images/ para a comparacao
	 *
	 */
	public void testGetExtension () {
		assertEquals("gif", ImageFilter.getExtension(new File(IMAGEDIR+"arvore_01.gif")));
		assertEquals("png", ImageFilter.getExtension(new File(IMAGEDIR+"dado_01.png")));
		assertEquals("jpg", ImageFilter.getExtension(new File(IMAGEDIR+"gato_01.jpg")));
		assertEquals("bmp", ImageFilter.getExtension(new File(IMAGEDIR+"navio_01.bmp")));
	}
	
	/**
	 * Testa o metodo accept()
	 * Utiliza imagens da pasta data/images/ para a comparacao
	 *
	 */
	public void testAccept() {
		assertTrue(imageFilter.accept(new File(IMAGEDIR+"arvore_01.gif")));
		assertTrue(imageFilter.accept(new File(IMAGEDIR+"dado_01.png")));
		assertTrue(imageFilter.accept(new File(IMAGEDIR+"gato_01.jpg")));
		assertTrue(imageFilter.accept(new File(IMAGEDIR+"navio_01.bmp")));
	}
	/**
	 * Testa o metodo getDescription()
	 *
	 */
	public void testGetDescription() {
		assertEquals("Apenas Imagens", imageFilter.getDescription());
	}
	
	/**
	 * Roda os testes
	 * @param args
	 */
	public static void main(String[] args) {
		junit.swingui.TestRunner.run(ImageFilterTest.class);
	}
	
	

}
