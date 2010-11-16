package util;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

/**
 * Classe que manipula arquivos
 * @author Abmar, Thiago
 *
 */
public class FileHandling {
	
	public final static String SEPARADOR = System.getProperty("file.separator");
	public final static String WWIDIR = "data" + SEPARADOR + "wwi" + SEPARADOR;
	public final static String IMAGEDIR = "data" + SEPARADOR + "images" + SEPARADOR;
	public final static String SOUNDSDIR = "data" + SEPARADOR + "sounds" + SEPARADOR;
	public final static String PLAYERSDIR = "data" + SEPARADOR + "plr" + SEPARADOR;
	
	/**
	 * Metodo que salva um objeto
	 * @param object O objeto a ser salvo
	 * @param filename O nome do arquivo onde o objeto sera salvo
	 * @throws IOException
	 */
	public static void save(Object object, String filename) throws IOException{
		
		FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(object);
		oos.close();		
	}
	
	/**
	 * Metodo que le um objeto
	 * @param filename O nome do arquivo de onde o objeto sera lido
	 * @return O objeto lido
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object read(String filename) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Object outObject = ois.readObject();
		ois.close();
		return outObject;
	}
	
		
	/**
	 * Redimensiona uma imagem e salva ela
	 * @param scale a escala da nova imagem em relacao a original
	 * @param srcImg a imagem de origem
	 * @return a imagem redimensionada
	 * @throws IOException
	 */
	public static BufferedImage resizeImage(double scale, BufferedImage srcImg) throws IOException {
		AffineTransformOp op = new AffineTransformOp
			(AffineTransform.getScaleInstance(scale, scale), null);
		return op.filter(srcImg, null);
	}
	
	/**
	 * Salva uma imagem
	 * @param srcImg a imagem original a ser salva
	 * @param destFile o arquivo de destino
	 * @throws IOException
	 */
	public static void saveImage(BufferedImage srcImg, String destFile) throws IOException {
		ImageIO.write(srcImg,"jpg",new File(destFile));
	}

	
}
