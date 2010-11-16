package tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.FileHandling;
import junit.framework.TestCase;

/**
 * Classe que testa os métodos de util.FileHandling
 * @author abmargb, thiagoss
 *
 */
public class FileHandlingTest extends TestCase {
	
	final static String IMAGEDIR = FileHandling.IMAGEDIR;
	
	/**
	 * Testa o método read()
	 *
	 */
	public void testRead() {
		try {
			Object o = FileHandling.read("FileNotFoud.fnf");
			o.toString();
			fail("Arquivo Nao Encontrado");
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
	}
	
	/**
	 * Testa o metodo resizeImage()
	 * Utiliza imagens da pasta data/images/ para a comparacao
	 *
	 */
	public void testResizeImage() {
		BufferedImage bi = new BufferedImage(250,250,BufferedImage.TYPE_INT_RGB);
		BufferedImage biNew = null;
		try {
			//Imagem de 500 x 500
			biNew = FileHandling.resizeImage(0.5 , ImageIO.read(new File(IMAGEDIR+"lapis_01.png")));
		} catch (IOException e) {
		}
		assertEquals(bi.getHeight(), biNew.getHeight());
		assertEquals(bi.getWidth(), biNew.getWidth());
	}
	
	
	/**
	 * Roda os testes
	 * @param args
	 */
	public static void main(String[] args) {
		junit.swingui.TestRunner.run(FileHandlingTest.class);
	}

}
